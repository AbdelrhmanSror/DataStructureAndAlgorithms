/*
 * Copyright 2019 Abdelrhman Sror. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 */



package trees

fun main() {

    val binarySearchTree = BinarySearchTree()
    binarySearchTree.insert(5)
    binarySearchTree.insert(6)
    binarySearchTree.insert(3)
    binarySearchTree.insert(2)
    binarySearchTree.insert(7)
    binarySearchTree.insert(4)

    binarySearchTree.printPostOrder()


}

data class Node(var value: Int, var left: Node? = null, var right: Node? = null, var height: Int = 0)

abstract class Tree {
    protected var root: Node? = null
    fun insert(value: Int) {
        root = insertRecursively(root, value)

    }

    fun delete(value: Int) {
        delete(root, value)
    }

    fun search(value: Int) {
        search(value) {
            print("node founded ${it.parent.value}  ${it.node.value}")
        }
    }

    protected abstract fun delete(root: Node?, value: Int): Node?

    protected abstract fun insertRecursively(node: Node?, value: Int): Node?

    fun printPreOrder(node: Node? = root) {
        if (node == null)
            return
        println(" ${node.value}")
        printPreOrder(node.left)
        printPreOrder(node.right)
    }

    fun printPostOrder(node: Node? = root) {
        if (node == null)
            return

        printPostOrder(node.left)
        printPostOrder(node.right)
        println(" ${node.value}")

    }

    fun printInorder(node: Node? = root) {
        if (node == null)
            return
        printInorder(node.left)
        print(" ${node.value}")
        printInorder(node.right)

    }

    private fun search(value: Int, parent: Node? = root, target: Node? = root, node: (((Child) -> Unit))) {
        when {
            target == null -> {
                print("node does not exist in tree")
                return
            }
            value > target.value -> {
                search(value, target, target.right, node)
            }
            value < target.value -> {
                search(value, target, target.left, node)
            }
            else -> {
                node(Child(parent!!, target))
            }

        }
    }


    protected fun findLargestNode(target: Node? = root): Node? {
        if (target != null) {
            return when (target.right) {
                null -> target
                else -> findLargestNode(target.right)
            }
        } else {
            print("no largest value")
        }
        return null

    }


}

class BinarySearchTree : Tree() {
    override fun delete(root: Node?, value: Int): Node? {
        when {
            root == null -> {
                print("node does not exist in tree")
                return root
            }
            value > root.value -> {
                root.right = delete(root.right, value)
            }
            value < root.value -> {
                root.left = delete(root.left, value)
            }
            else -> {
                //if node is a leaf just delete it
                if (root.left == null && root.right == null) {
                    return null
                }
                //node to delete has no left child so set right child of deleted node to the the parent of deleted node
                else if (root.left == null) {
                    return root.right
                }
                //node to delete has no right child so set left child of deleted node to the the parent of deleted node
                else if (root.right == null) {
                    return root.left
                } else {
                    //find the largest node in the left subtree of the root
                    val largestNode = findLargestNode(root.left)
                    largestNode?.let {
                        //replacing the value of root with the largest node value
                        root.value = it.value
                        //modify the parent so it points to the left or right  subTree of largest node if exist
                        root.left = delete(root.left, it.value)

                    }

                }
            }

        }

        return root
    }


    override fun insertRecursively(node: Node?, value: Int): Node? {
        when {
            node == null -> return Node(value)
            value > node.value //insert right
            -> {
                node.right = insertRecursively(node.right, value)

            }
            value < node.value//insert left
            -> {
                node.left = insertRecursively(node.left, value)
            }
            else -> throw Exception("Key Repetition")
        }
        return node
    }

}
