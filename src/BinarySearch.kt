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

    val myList = arrayOf(1, 5, 6, 30, 60, 65, 70)
    val myBinarySearch = MyBinarySearch(myList, 65)
    println("the number that is found is ${myBinarySearch.doLoopSearch()}")
    print("the number that is found is ${myBinarySearch.doRecursionSearch()}")

}

/**
 * has two options
 * first:do binary search with traditional for loop
 * second: do binary search with recursion
 * the array must be sorted
 *its complexity is log(n)
 */
class MyBinarySearch(private val arrayToSearchIn: Array<Int>, private val elementToSearch: Int) {

    /**
     * @return the number if found otherwise return -1
     */
    fun doLoopSearch(): Int {
        var firstIndex = 0
        var lastIndex = arrayToSearchIn.size - 1
        while (firstIndex <= lastIndex) {
            val middle = (firstIndex + lastIndex) / 2
            when {
                arrayToSearchIn[middle] < elementToSearch -> {
                    firstIndex = middle + 1
                }
                arrayToSearchIn[middle] > elementToSearch -> {
                    lastIndex = middle - 1
                }
                arrayToSearchIn[middle] == elementToSearch -> {
                    return elementToSearch
                }
            }

        }
        return -1
    }

    fun doRecursionSearch(low: Int = 0, high: Int = arrayToSearchIn.size - 1): Int {
        val middle = (low + high) / 2
        when (low <= high) {
            arrayToSearchIn[middle] < elementToSearch -> {
                return doRecursionSearch(middle + 1, high)
            }
            arrayToSearchIn[middle] > elementToSearch -> {
                return doRecursionSearch(low, middle - 1)
            }
            arrayToSearchIn[middle] == elementToSearch -> {
                return elementToSearch
            }
        }
        return -1
    }
}