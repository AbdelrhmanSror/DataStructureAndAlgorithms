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
    val array = arrayOf(1, 11, 0, 5, 3, 10, 8, 30)
    val insertionSort = InsertionSort(array)
    insertionSort.doSort()
    print(array.printArray())
}

/**
 * complexity =o(n^2)
 * In general, insertion sort will write to the array O(n2) times, whereas selection sort will write only O(n) times.
 * For this reason selection sort may be preferable in cases where writing to memory is significantly more expensive than reading,
 * such as with EEPROM or flash memory.
 */
class InsertionSort(private val array: Array<Int>) {

    fun doSort() {
        for (i in array.indices) {
            var innerLoop = i - 1
            val key = array[i]
            while (innerLoop >= 0 && array[innerLoop] > key) {
                array[innerLoop + 1] = array[innerLoop]
                innerLoop--
            }
            array[innerLoop + 1] = key
        }
    }

}