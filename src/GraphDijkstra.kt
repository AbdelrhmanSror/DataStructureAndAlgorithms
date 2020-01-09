import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val graphDijkstra = GraphDijkstra()
    graphDijkstra.addNeighbour(
        "0"
        , "1" to 4
        , "7" to 8
    )
    graphDijkstra.addNeighbour(
        "1"
        , "0" to 4, "7" to 11, "2" to 8
    )
    graphDijkstra.addNeighbour(
        "7"
        , "1" to 11
        , "0" to 8, "8" to 7, "6" to 1
    )
    graphDijkstra.addNeighbour(
        "2"
        , "8" to 2
        , "5" to 4, "3" to 7
    )
    graphDijkstra.addNeighbour(
        "8"
        , "2" to 2
        , "7" to 7, "6" to 6
    )
    graphDijkstra.addNeighbour(
        "6"
        , "8" to 6
        , "7" to 1, "5" to 2
    )
    graphDijkstra.addNeighbour(
        "3"
        , "2" to 7
        , "5" to 14, "4" to 9
    )
    graphDijkstra.addNeighbour(
        "4"
        , "3" to 9
        , "5" to 10
    )
    graphDijkstra.addNeighbour(
        "5"
        , "6" to 2
        , "2" to 4, "3" to 14, "4" to 10
    )


    graphDijkstra.addNeighbour("4")
    graphDijkstra.calculateFastestPath("0", "8")


}


/**
 * works only with  non negative weights
 * use bellman ford instead if u want to work with  negative weights
 */
class GraphDijkstra {
    //graph that stores all the nodes with its neighbours and cost
    private val graph = HashMap<String, LinkedList<Pair<String, Int>>>()
    //cost table to represent the cost of each node from start point
    private val costMap = HashMap<String, Int>()
    //parent table to represent the parent of each node
    //represent child to parent
    private val parentMap = HashMap<String, String>()
    private val nodesToVisit = LinkedList<String>()
    private val visitedNodes = HashSet<String>()


    fun addNeighbour(parent: String, vararg pair: Pair<String, Int>) {
        if (!graph.containsKey(parent)) {
            graph[parent] = LinkedList()
        }
        graph[parent]?.addAll(pair)
    }

    fun calculateFastestPath(from: String, to: String) {
        if (graph.isEmpty())
            throw Exception("there is no graph to start searching in")
        if (!graph.containsKey(to) || !graph.containsKey(from))
            throw Exception("invalid node ")
        costMap[from] = 0
        parentMap[from] = "" //start point has no parent
        nodesToVisit.add(from)
        while (true) {
            val minNode = nodesToVisit.poll()
            if (minNode == null) {
                printFastestPathWithCost(from, to)
                break
            }
            val min: Int = costMap[minNode]!!
            //update the node neighbours
            graph[minNode]?.forEach {
                //update the value in cost table if only the new cost is less than the old cost
                //i have assumed that any node does not exist in the cost table has an infinite value
                //so any value that comes after will for sure be the least one
                //so i create new table with that node with its new value
                //otherwise update the value in that table
                if (costMap[it.first] == null || (it.second + min) < costMap[it.first]!!) {
                    costMap[it.first] = it.second + min
                    parentMap[it.first] = minNode
                    if (!visitedNodes.contains(it.first))
                        nodesToVisit.add(it.first)
                }

            }
            visitedNodes.add(minNode)
        }


    }

    private fun printFastestPathWithCost(from: String, to: String) {
        println("${costMap}")
        println("${parentMap}")
        println("fastest path from $from to $to is")
        printPath(from, to)

    }

    private fun printPath(from: String, to: String) {
        if (to == from) {
            print("$from ")
            return
        }
        if (!parentMap.containsKey(to)) {
            print("NONE  ")
            return
        } else
            print("$to <-- ")

        printPath(from, parentMap.getValue(to))
    }


}