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

import kotlin.math.max

fun main() {
    /*  AVL Tree would be
           4
          /  \
        2     6
       /  \   |  \
      1    3  5   7
    */
    val avlTrees = AvlTrees()
    /* avlTrees.insert(1)
     avlTrees.insert(2)
     avlTrees.insert(3)
     avlTrees.insert(4)
     avlTrees.insert(6)
     avlTrees.insert(5)
     avlTrees.insert(7)*/
    avlTrees.insert(30)
    avlTrees.insert(26)
    avlTrees.insert(60)
    avlTrees.insert(28)
    avlTrees.insert(55)
    avlTrees.insert(70)
    avlTrees.insert(25)
    avlTrees.insert(29)
    avlTrees.insert(27)
    avlTrees.search(28)

}

data class Child(val parent: Node, val node: Node)
class AvlTrees : Tree() {

    override fun insert(value: Int) {
        root = insertRecursively(root, value)
    }

    fun search(value: Int) {
        search(value) {
            print("node founded ${it.parent.value}  ${it.node.value}")
        }
    }


    private fun search(value: Int, parent: Node? = root, target: Node? = root, node: ((Child) -> Unit)) {
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
            //node founded
            else -> {
                node(Child(parent!!, target))
            }
        }
    }


    fun delete(value: Int) {
        search(value) { nodeToDelete ->
            //if node is a leaf just delete it
            if (nodeToDelete.node.left == null && nodeToDelete.node.right == null) {
                deleteNode(nodeToDelete.parent, nodeToDelete.node, null)
            }
            //node to delete has no left child so set right child of deleted node to the the parent of deleted node
            else if (nodeToDelete.node.left == null) {
                deleteNode(nodeToDelete.parent, nodeToDelete.node, nodeToDelete.node.right)
            }
            //node to delete has no right child so set left child of deleted node to the the parent of deleted node
            else if (nodeToDelete.node.right == null) {
                deleteNode(nodeToDelete.parent, nodeToDelete.node, nodeToDelete.node.left)
            } else {
                //find the largest node in the left subtree of the nodeToDelete
                findLargestNode(nodeToDelete.node, nodeToDelete.node.left) {
                    //modify the parent so it points to the left or right  subTree of deleted node if exist
                    deleteNode(it.parent, it.node, it.node.left)
                    //replacing the value of nodeToDelete with the largest value
                    nodeToDelete.node.value = it.node.value

                }
            }

        }

    }

    private fun deleteNode(parent: Node, nodeToDelete: Node, newNode: Node?) {
        //the node to delete is positioned at left side of the parent
        if (nodeToDelete.value < parent.value)
            parent.left = newNode
        //the node to delete is positioned at right side of the parent
        else parent.right = newNode

    }


    fun findLargestNode(parent: Node? = root, target: Node? = root, node: ((Child) -> Unit)) {
        if (target != null) {
            when (target.right) {
                null -> {
                    node(Child(parent!!, target))
                    return
                }
                else -> {
                    findLargestNode(target, target.right, node)
                }
            }
        } else {
            print("no largest value")
        }
    }

    private fun insertRecursively(node: Node?, value: Int): Node? {
        var balanceFactor: Int = 0
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
            else -> return node
        }
        node.height = getHeight(node) + 1
        balanceFactor = getBalanceFactor(node)
        when {
            //if the unbalanced tree exist and value is smaller than the value in the left node then perform right rotation
            //left left case
            balanceFactor > 1 && value < node.left!!.value -> {
                return performRightRotation(node)
            }
            //if the unbalanced tree exist and value is larger than the value in the right node then perform left rotation
            //right right case
            balanceFactor < -1 && value > node.right!!.value -> {
                return performLeftRotation(node)
            }
            //if the unbalanced tree exist and value is larger than the value in the right of left node then perform left right rotation
            //left right case
            balanceFactor > 1 && value > node.left!!.value -> {
                node.left = performLeftRotation(node.left!!)
                return performRightRotation(node)

            }
            //if the unbalanced tree exist and value is smaller than the value in the left of right node then perform right left rotation
            //right left case
            balanceFactor < -1 && value < node.right!!.value -> {
                node.right = performLeftRotation(node.right!!)
                return performRightRotation(node)
            }
        }

        return node

    }

    private fun performLeftRotation(parent: Node): Node {
        //save the parent right and left of right child
        val newParent = parent.right
        val childLeft = newParent!!.left
        newParent.left = parent
        parent.right = childLeft
        updateHeights(parent, newParent)
        return newParent

    }

    private fun performRightRotation(parent: Node): Node {
        //save the parent right and left of right child
        val newParent = parent.left
        val childRight = newParent!!.right
        newParent.right = parent
        parent.left = childRight
        updateHeights(parent, newParent)
        return newParent

    }

    private fun updateHeights(child: Node, newParent: Node) {
        //update height of child first to reflect on parents
        child.height = getHeight(child) + 1
        newParent.height = getHeight(newParent) + 1
    }

    private fun getHeight(node: Node): Int {
        return max(node.left?.height ?: 0, node.right?.height ?: 0)
    }

    private fun getBalanceFactor(node: Node): Int {
        return (node.left?.height ?: 0) - (node.right?.height ?: 0)
    }

}