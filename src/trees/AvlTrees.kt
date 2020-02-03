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
    val avlTrees = AvlTrees()
    avlTrees.insert(29)
    avlTrees.printInorder()

}

/**
 * The AVL tree and other self-balancing search trees like Red Black are useful to get all basic operations done in O(log n) time.
 * The AVL trees are more balanced compared to Red-Black Trees, but they may cause more rotations during insertion and deletion.
 * So if your application involves many frequent insertions and deletions, then Red Black trees should be preferred.
 * And if the insertions and deletions are less frequent and search is the more frequent operation,
 * then AVL tree should be preferred over Red Black Tree.
 */
data class Child(val parent: Node, val node: Node)

class AvlTrees : Tree() {

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

        return deletionBalance(root)
    }

    override fun insertRecursively(node: Node?, value: Int): Node? {
        when {
            node == null -> return Node(value)
            //insert right
            value > node.value
            -> {
                node.right = insertRecursively(node.right, value)
            }
            //insert left
            value < node.value
            -> {
                node.left = insertRecursively(node.left, value)
            }
            else -> return node
        }

        return insertionBalance(node, value)

    }

    private fun insertionBalance(node: Node, value: Int): Node {
        var balanceFactor = 0
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

    private fun deletionBalance(node: Node): Node {
        var balanceFactor = 0
        node.height = getHeight(node) + 1
        balanceFactor = getBalanceFactor(node)
        when {
            //if the unbalanced tree exist and value is smaller than the value in the left node then perform right rotation
            //left left case
            balanceFactor > 1 && getBalanceFactor(node.left!!) >= 0 -> {
                return performRightRotation(node)
            }
            //if the unbalanced tree exist and value is larger than the value in the right node then perform left rotation
            //right right case
            balanceFactor < -1 && getBalanceFactor(node.right!!) <= 0 -> {
                return performLeftRotation(node)
            }
            //if the unbalanced tree exist and value is larger than the value in the right of left node then perform left right rotation
            //left right case
            balanceFactor > 1 && getBalanceFactor(node.left!!) < 0 -> {
                node.left = performLeftRotation(node.left!!)
                return performRightRotation(node)

            }
            //if the unbalanced tree exist and value is smaller than the value in the left of right node then perform right left rotation
            //right left case
            balanceFactor < -1 && getBalanceFactor(node.right!!) > 0 -> {
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
