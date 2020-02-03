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

import kotlin.math.max

fun main() {
    print(findMaxSubset(arrayOf(2, 1, 5, 8, 4)))
}


/**
 * kadane's algorithm
 */
fun maxSubArraySum(a: Array<Int>): Int {
    val size = a.size
    var max_so_far = Int.MIN_VALUE
    var max_ending_here = 0
    for (i in 0 until size) {
        max_ending_here += a[i]
        if (max_so_far < max_ending_here) max_so_far = max_ending_here
        if (max_ending_here < 0) max_ending_here = 0
    }
    return max_so_far
}

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