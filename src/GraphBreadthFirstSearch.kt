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

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

//alias name for class
typealias BFS = GraphBreadthFirstSearch

fun main() {
    val bfs = BFS()
    bfs.addConnection("You", "Claire", "Bob", "Alice")
    bfs.addConnection("Alice", "Peggy")
    bfs.addConnection("Bob", "Peggy", "Anuj")
    bfs.addConnection("Anuj", "")
    bfs.addConnection("Peggy", "")
    bfs.addConnection("Claire", "Thom", "Jonny")
    bfs.addConnection("Thom", "")
    bfs.addConnection("Jonny", "")
    bfs.searchGraph("Peggy", "You")


}

/**
 * If you search your entire network for a person,
 * that means you’ll follow each edge
 * So the running time is at least O(number of edges). You also keep a queue of every person to search.
 * Adding one person to the queue takes constant time: O(1). Doing this for every person will take O(number of people) total.
 * Breadth-first search takes O(number of people + number of edges), and it’s more commonly written as O(V+E) (V for number of vertices,
 * E for number of edges).
 */
class GraphBreadthFirstSearch {
    //first name in the hash map to use it to start search from later
    private lateinit var first: String
    private var connections: HashMap<String, LinkedList<String>> = HashMap()

    /**
     *if the parent does not exist we create it and then add child to it later
     */
    fun addConnection(parent: String, vararg child: String) {
        if (!::first.isInitialized) {
            first = parent
        }
        if (!connections.containsKey(parent)) {
            connections[parent] = LinkedList()
        }
        connections[parent]?.addAll(child)
    }

    fun searchGraph(connection: String, optionalStartFrom: String? = null) {
        if(connections.isEmpty())
            throw Exception("there is no graph to start searching in")
        val startingPoint = optionalStartFrom ?: first
        val listOfConnectionToVisit= LinkedList<String>()
        val visited= HashSet<String>()
        if(!connections.containsKey(startingPoint))
            throw Exception("starting point does not exist in this graph,write a valid starting point")
        if(connection==startingPoint){
            print(" traversal points is $connection ")
        } else{
            listOfConnectionToVisit.add(startingPoint)
            visited.add(startingPoint)
            while (!listOfConnectionToVisit.isEmpty()) {
                val key=listOfConnectionToVisit.poll()
                connections[key]?.forEach {
                    if (it != connection) {
                        if(!visited.contains(it)) {
                            println("traverse $it from $key  ")
                            visited.add(it)
                            listOfConnectionToVisit.add(it)
                        }
                    } else {
                        println("path found $it from $key ")
                        return
                    }

                }

            }
            println("path  not found ")


        }


    }

}