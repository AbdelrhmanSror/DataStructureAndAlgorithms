package kruskalsMst

import java.util.*
import kotlin.collections.HashMap

fun main() {
    val cycleDetection = CycleDetection<Int>()
    cycleDetection.isCycleExist(0 to 1, 1 to 2, 2 to 3, 3 to 4, 4 to 5, 5 to 2)
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

    fun createIfNotExist(src: T, des: T) {
        if (!nodeMap.containsKey(src))
            nodeMap[src] = Node(src, 0)
        if (!nodeMap.containsKey(des))
            nodeMap[des] = Node(des, 0)


    }

}