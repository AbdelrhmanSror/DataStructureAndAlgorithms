import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
//alias name for class
typealias BFS = GraphBreadthFirstSearch
fun main() {
    val bfs=BFS()
    bfs.addConnection("You","Bob","Claire","Alice")
    bfs.addConnection("Bob","You","Peggy","Anuj")
    bfs.addConnection("Anuj","Bob")
    bfs.addConnection("Peggy","Bob","Alice")
    bfs.addConnection("Claire","You","Thom","Jonny")
    bfs.addConnection("Thom","Claire")
    bfs.addConnection("Jonny","Claire")
    bfs.searchGraph("Anuj","Thom")







}

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
            throw Exception("there is no connections to start searching in")
        val startingPoint = optionalStartFrom ?: first
        val listOfConnectionToVisit= LinkedList<String>()
        val visited= HashSet<String>()
        if(!connections.containsKey(startingPoint))
            throw Exception("starting point does not exist in this graph,write a valid starting point")
        if(connection==startingPoint){
            print(" traversal points is $connection ")
        }
        else{
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
                            println("target found $it from $key ")
                            return
                        }

                    }

            }
            println("connection  not found ")



        }



    }

}