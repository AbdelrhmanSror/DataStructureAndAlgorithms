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

    //println("${findGreatestCommonDivisor(640, 400)}")
    sumArrayByRecursion(arrayOf(1, 2, 4, 6, 8))

}


fun findGreatestCommonDivisor(first: Int, second: Int): Int {
    return when {
        first == 0 -> second
        second == 0 -> first
        else -> {
            findGreatestCommonDivisor(second, first % second)

        }
    }
}


fun sumArrayByRecursion(array: Array<Int>, total: Int = 0, index: Int = 0) {
    if (index >= array.size && array.isNotEmpty()) {
        println("total is $total")
        return
    }
    sumArrayByRecursion(array, total + array[index], index + 1)
}