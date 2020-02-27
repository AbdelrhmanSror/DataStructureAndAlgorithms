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

package minimumSpanningTree

import java.util.*
import kotlin.collections.HashMap

fun main() {
    val cycleDetection = CycleDetection<Int>()
   print(cycleDetection.isCycleExist(3 to 1, 3 to 4, 3 to 2, 2 to 4, 2 to 3, 4 to 3, 4 to 1, 4 to 2, 1 to 3, 1 to 4))
}

data class Node<T>(var parent: T, var rank: Int = 0)
/**
 * class for detecting if there is a cycle in graph or not using union rank and path compression
 * complexity in worst case is log(n) which is much more better than naive solution which take liner time in worst case
 */
class CycleDetection<T> {
    private val nodeMap = HashMap<T, Node<T>>()
    //pair with src and destination
    private val edges = LinkedList<Pair<T, T>>()

    fun isCycleExist(vararg pair: Pair<T, T>): Boolean {
        pair.forEach {
            createIfNotExist(it.first, it.second)
        }
        edges.addAll(pair)
        pair.forEach {
            val src = findRoot(it.first)
            val des = findRoot(it.second)
            if (src == des) {
                return true
            }
            union(src, des)

        }

        return false

    }

    private fun findRoot(node: T): T {
        //here this if statement is as if i said [if(nodeMap[node].parent!=node)]
        if (nodeMap[node]!!.parent == node) {
            return node
        }
        return findRoot(nodeMap[node]!!.parent)

    }


    private fun union(src: T, des: T) {
        when {
            nodeMap[src]!!.rank > nodeMap[des]!!.rank -> {
                nodeMap[des]?.parent = src

            }
            nodeMap[src]!!.rank < nodeMap[des]!!.rank -> {
                nodeMap[src]?.parent = des


            }
            else -> {
                nodeMap[src]!!.parent = des
                nodeMap[des]!!.rank++


            }
        }

    }

    private fun createIfNotExist(src: T, des: T) {
        if (!nodeMap.containsKey(src))
            nodeMap[src] = Node(src, 0)
        if (!nodeMap.containsKey(des))
            nodeMap[des] = Node(des, 0)


    }

}