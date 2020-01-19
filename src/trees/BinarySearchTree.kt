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
    abstract fun insert(value: Int)
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

}

class BinarySearchTree : Tree() {
    override fun insert(value: Int) {
        root = insertRecursively(root, value)
    }

    private fun insertRecursively(node: Node?, value: Int): Node? {
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