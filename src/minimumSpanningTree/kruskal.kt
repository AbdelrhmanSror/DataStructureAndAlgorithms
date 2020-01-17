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
import kotlin.collections.HashSet


fun main() {
    val kruskal = Kruskal<Int>()
    kruskal.setEdge(
        Edge(0 to 1, 4)
        , Edge(0 to 7, 8)
        , Edge(1 to 7, 11)
        , Edge(1 to 2, 8)
        , Edge(2 to 8, 2)
        , Edge(8 to 7, 7)
        , Edge(8 to 6, 6)
        , Edge(7 to 6, 1)
        , Edge(2 to 5, 4)
        , Edge(6 to 5, 2)
        , Edge(2 to 3, 7)
        , Edge(3 to 4, 9)
        , Edge(5 to 4, 10)
    )
    kruskal.findMST()

}

data class Edge<T>(val path: Pair<T, T>, val weight: Int)
class EdgeComparator<T> : Comparator<Edge<T>> {
    override fun compare(o1: Edge<T>, o2: Edge<T>): Int {
        return when {
            o1.weight < o2.weight -> -1
            o1.weight > o2.weight -> 1
            else -> 0
        }
    }
}

class Kruskal<T> {
    //priority queue to sort all the edges in ascending order using its weight
    private val edgesQueue = PriorityQueue<Edge<T>>(EdgeComparator<T>())
    //hash set to store minimum spanning tree
    private val constructedGraph = HashSet<Edge<T>>()
    private val cycleDetection = CycleDetection<T>()
    fun setEdge(vararg edges: Edge<T>) {
        edgesQueue.addAll(edges)


    }

    fun findMST() {
        arrayOf(5).sort()
        /**
         * priority queue iterator  does not guarantee to traverse the elements of the priority queue in any particular order
         * so i have to make a work around by calling poll on priority queue because for sure it will return the least item in order
         */
        while (!edgesQueue.isEmpty()) {
            val minEdge = edgesQueue.poll()
            /**
             * if cycle not exist then we add it to [constructedGraph]
             */
            if (!cycleDetection.isCycleExist(minEdge.path)) {
                constructedGraph.add(minEdge)

            }

        }
        println(constructedGraph)

    }


}



