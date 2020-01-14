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

fun main() {
    val array = arrayOf(1, 11, 2, 10, 3, 9, 4, 8)
    MyQuickSort(array).doQuickSort()
    array.printArray()


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

    fun doQuickSort(low: Int = 0, high: Int = array.size - 1) {
        if (high <= low) {
            return
        }
        val pivot = doPartitioning(low, high)
        doQuickSort(low, pivot - 1) //the left side
        doQuickSort(pivot + 1, high) //the right side
    }

    private fun doPartitioning(low: Int, high: Int): Int {
        //assume that our initial pivot is last index in array
        val pivot = array[high]
        var i = low - 1
        for (j in low until high) {
            if (array[j] < pivot) {
                i++
                array.doSwapping(i, j)
            }
        }
        i++
        array.doSwapping(i, high)
        return i
    }

    private fun Array<Int>.doSwapping(first: Int, last: Int) {
        val temp = this[last]
        this[last] = this[first]
        this[first] = temp
    }
}