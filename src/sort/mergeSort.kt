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

fun main() {
    val array = arrayOf(1, 2, 5, 3, 4, 8, 7, 6)
    mergeSort(array)
    array.forEach { print(it) }

}

fun mergeSort(arr: Array<Int>, temp: Array<Int> = Array(arr.size) { 0 }) {
    sort(arr, temp, 0, arr.size - 1)
}

private fun sort(arr: Array<Int>, temp: Array<Int>, start: Int, end: Int) {
    if (start >= end) {
        return
    }
    // Find the middle point
    val middle = (start + end) / 2

    // Sort first and second halves
    sort(arr, temp, start, middle)
    sort(arr, temp, middle + 1, end)
    // Merge the two halves
    merge(arr, temp, start, end)

}


fun merge(arr: Array<Int>, temp: Array<Int>, leftStart: Int, rightEnd: Int) {
    val leftEnd = (leftStart + rightEnd) / 2
    val rightStart = leftEnd + 1
    val size = rightEnd - leftStart + 1
    var left = leftStart
    var right = rightStart
    var index = leftStart
    while (left <= leftEnd && right <= rightEnd) {
        if (arr[left] <= arr[right]) {
            temp[index] = arr[left]
            left++
        } else {
            temp[index] = arr[right]
            right++
        }
        index++
    }

    System.arraycopy(arr, left, temp, index, leftEnd - left + 1)
    System.arraycopy(arr, right, temp, index, rightEnd - right + 1)
    System.arraycopy(temp, leftStart, arr, leftStart, size)

}
