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

package dynamicProgramming

import java.util.*
import kotlin.math.max


fun main() {

    val result = getMaxSum(arrayOf(3, 3, 9), 7)

    println(result)

}


/**
 * kadane's algorithm
 * adjacent
 */
fun maxSubArraySum(a: Array<Int>): Int {
    val size = a.size
    var maxSoFar = Int.MIN_VALUE
    var maxEndingHere = 0
    for (i in 0 until size) {
        maxEndingHere += a[i]
        if (maxSoFar < maxEndingHere) maxSoFar = maxEndingHere
        if (maxEndingHere < 0) maxEndingHere = 0
        println("maxsofar $maxSoFar   max endinghere$maxEndingHere")
    }
    return maxSoFar
}

/**
 * Maximum subarray sum modulo m

 */
fun getMaxSum(arr: Array<Long>, m: Long): Long {
    var maxSum: Long = 0
    val prefix = TreeSet<Long>()
    var currentSum: Long = 0
    for (element in arr) {
        currentSum = (currentSum + element % m) % m
        val set = prefix.tailSet(currentSum + 1)
        val itr: Iterator<Long> = set.iterator()
        if (itr.hasNext()) {
            maxSum = max(maxSum, (currentSum - itr.next() + m) % m)
        }
        maxSum = max(maxSum, currentSum)
        prefix.add(currentSum)
    }
    return maxSum
}

/**
 * non adjacent subarray elemnt
 */
fun findMaxSubset(arr: Array<Int>): Int {
    var maxWithPrevElement = arr[0]
    var maxWithOutPrevElement = 0
    var maxWithCurrentElement = 0
    for (i in 1 until arr.size) {
        maxWithCurrentElement = arr[i] + maxWithOutPrevElement
        maxWithOutPrevElement = max(maxWithOutPrevElement, maxWithPrevElement)
        maxWithPrevElement = maxWithCurrentElement
    }

    return max(maxWithOutPrevElement, maxWithPrevElement)
}