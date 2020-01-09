import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet


fun main() {
    val graphDijkstra = GraphDijkstra()
    /* graphDijkstra.addNeighbour(
         "U"
         , "A" to 4
         , "D" to 3, "C" to 6
     )
     graphDijkstra.addNeighbour(
         "A"
         , "U" to 4, "I" to 7
     )
     graphDijkstra.addNeighbour(
         "D"
         , "U" to 3
         , "C" to 4
     )
     graphDijkstra.addNeighbour(
         "C"
         , "U" to 6
         , "D" to 4, "I" to 4
     )
     graphDijkstra.addNeighbour(
         "I"
         , "A" to 7
         , "Y" to 4, "C" to 4
     )
     graphDijkstra.addNeighbour(
         "Y"
         , "I" to 4
         , "T" to 5
     )
     graphDijkstra.addNeighbour(
         "T"
         , "C" to 5
         , "Y" to 5
     )
    */
    graphDijkstra.addNeighbour(
        "S"
        , "A" to 6
        , "B" to 2
    )
    graphDijkstra.addNeighbour(
        "A"
        , "F" to 1
    )
    graphDijkstra.addNeighbour(
        "B"
        , "F" to 5
        , "A" to 3
    )
    graphDijkstra.addNeighbour(
        "F"
    )
    graphDijkstra.calculateFastestPath("S", "F")


}

/**
 * just for fun of using extension function and dataclass ,i could use pair and rest my self i know
 */
class Node(var node: String, var cost: Int)

object NodeComparator : Comparator<Node> {
    override fun compare(o1: Node, o2: Node): Int {
        return when {
            o1.cost < o2.cost -> -1
            o1.cost > o2.cost -> 1
            else -> 0
        }
    }

}

fun List<Pair<String, Int>>.toNode(): List<Node> {
    return this.map {
        Node(it.first, it.second)
    }

}

/**
 * works only with  non negative weights
 * use bellman ford instead if u want to work with  negative weights
 */
class GraphDijkstra {
    //graph that stores all the nodes with its neighbours and cost
    private val graph = HashMap<String, LinkedList<Node>>()
    //cost table to represent the cost of each node from start point
    private val costMap = HashMap<String, Int>()
    //parent table to represent the parent of each node
    //represent child to parent
    private val parentMap = HashMap<String, String>()
    private val nodesToVisit = PriorityQueue<Node>(NodeComparator)
    private val visitedNodes = HashSet<String>()


    fun addNeighbour(parent: String, vararg pair: Pair<String, Int>) {
        if (!graph.containsKey(parent)) {
            graph[parent] = LinkedList()
        }
        graph[parent]?.addAll(pair.toList().toNode())
    }

    fun calculateFastestPath(from: String, to: String) {
        if (graph.isEmpty())
            throw Exception("there is no graph to start searching in")
        if (!graph.containsKey(to) || !graph.containsKey(from))
            throw Exception("invalid node ")
        costMap[from] = 0
        parentMap[from] = "" //start point has no parent
        nodesToVisit.add(Node(from, 0))
        while (true) {
            val minNode = nodesToVisit.poll()
            if (minNode == null) {
                printFastestPathWithCost(from, to)
                break
            }
            val min: Int = costMap[minNode.node]!!
            //update the node neighbours
            graph[minNode.node]?.forEach {
                //update the value in cost table if only the new cost is less than the old cost
                //i have assumed that any node does not exist in the cost table has an infinite value
                //so any value that comes after will for sure be the least one
                //so i create new table with that node with its new value
                //otherwise update the value in that table
                if (costMap[it.node] == null || (it.cost + min) < costMap[it.node]!!) {
                    val cost = it.cost + min
                    costMap[it.node] = cost
                    parentMap[it.node] = minNode.node
                    if (!visitedNodes.contains(it.node)) {
                        visitedNodes.add(it.node)
                        nodesToVisit.add(Node(it.node, cost))
                    }
                }

            }
        }


    }

    private fun printFastestPathWithCost(from: String, to: String) {
        println("$costMap")
        println("$parentMap")
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
