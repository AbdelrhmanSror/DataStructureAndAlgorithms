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

package sort

import java.util.*
import kotlin.Comparator

fun main() {

    val scan = Scanner(System.`in`)
    val arr = Array<Int>(10000) { 0 }
    for (i in 0 until 10000) {
        val arrItem = scan.nextLine().trim().toInt()
        arr[i] = arrItem
    }
    println(MyQuickSort(arr).doQuickSort())
    arr.printArray()


}

fun <T> Array<T>.printArray() {
    val n = this.size
    for (i in 0 until n)
        println(this[i].toString())

}

/**
 * worst case would be O(n^2) because at worst case the array would already be sorted and we waste time on already sorted array
 * best and average case is O(nlog(n)) which is pretty much good
 */
class MyQuickSort(private val array: Array<Int>) {

    fun doQuickSort(low: Int = 0, high: Int = array.size - 1): Int {
        var comparison = 0
        if (high <= low) {
            return 0
        }
        val pivot = doPartitioning(low, high)
        comparison += ((low + pivot.first) / 2) + ((pivot.second + high) / 2)
        doQuickSort(low, pivot.first) //the left side
        doQuickSort(pivot.second, high) //the right side
        return comparison
    }

    private fun doPartitioning(low: Int, high: Int): Pair<Int, Int> {
        //assume that our initial pivot is middle index in array
        val pivot = array.choosePivot(low, high)
        return when (pivot.second) {
            low -> {
                doPartitioningFirst(low, high)
            }
            high -> {
                doPartitioningLast(low, high)
            }
            else -> {
                doPartitioningMiddle(low, high, pivot.second)
            }
        }
    }

    private fun doPartitioningLast(low: Int, high: Int): Pair<Int, Int> {
        //assume that our initial pivot is last index in array
        val pivot = array[high]
        var i = low - 1
        for (j in low..high) {
            if (array[j] < pivot) {
                array.doSwapping(++i, j)
            }
        }
        array.doSwapping(++i, high)
        return Pair(i - 1, i + 1)
    }

    private fun doPartitioningFirst(low: Int, high: Int): Pair<Int, Int> {
        //assume that our initial pivot is first index in array
        val pivot = array[low]
        var i = high + 1
        for (j in high downTo low) {
            if (array[j] > pivot) {
                array.doSwapping(--i, j)
            }
        }
        array.doSwapping(--i, low)
        return Pair(i - 1, i + 1)
    }

    private fun doPartitioningMiddle(low: Int, high: Int, middle: Int): Pair<Int, Int> {
        //assume that our initial pivot is middle index in array
        val pivot = array[middle]
        var i = low
        var j = high
        while (i <= j) {
            while (array[i] < pivot) i++
            while (array[j] > pivot) j--
            if (i <= j) {
                array.doSwapping(i, j)
                i++; j--
            }
        }
        return Pair(j, i)
    }


    private fun Array<Int>.doSwapping(first: Int, last: Int) {
        val temp = this[last]
        this[last] = this[first]
        this[first] = temp
    }

    private fun Array<Int>.choosePivot(low: Int, high: Int): Pair<Int, Int> {
        val temp = Array<Pair<Int, Int>?>(3) { null }
        val middle = (low + high) / 2
        temp[0] = Pair(this[low], low)
        temp[1] = Pair(this[high], high)
        temp[2] = Pair(this[middle], middle)
        temp.sortWith(Comparator { o1, o2 ->
            o1!!.first.compareTo(o2!!.second)
        })
        return temp[1]!!

    }
}
