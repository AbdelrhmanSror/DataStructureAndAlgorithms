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

import java.util.*

fun main() {
    val listOfArtist: ArrayList<Pair<String, Int>> = arrayListOf(
        Pair("a", 50)
        , Pair("b", 10)
        , Pair("c", 60)
        , Pair("d", 5)
        , Pair("e", 40)
        , Pair("f", 11)
        , Pair("g", 30)
        , Pair("h", 4)
        , Pair("i", 100)
        , Pair("j", 70)
    )
    val mySelectionSort = MySelectionSort(listOfArtist.toInt())
    println("the list after sorting is  ${mySelectionSort.doSort()}")


}


/**
 * will do selection sort on list of artist based on number of time that their song has been played
 * it has a complexity of O(n^2)
 * In general, insertion sort will write to the array O(n2) times, whereas selection sort will write only O(n) times.
 * For this reason selection sort may be preferable in cases where writing to memory is significantly more expensive than reading,
 * such as with EEPROM or flash memory.
 */
data class MySelectionSort(private val list: ArrayList<Int>) {
    fun doSort(): List<Int> {
        run loop@{
            list.forEachIndexed { index, i ->
                var largeIndex = index
                //if we have received last element then add it to list and retrun the new sorted list
                if (index == list.size - 1) {
                    return@loop
                }
                for (item in index + 1 until list.size) {
                    if (list[largeIndex] < list[item]) {
                        largeIndex = item
                    }
                }

                list.swap(index, largeIndex)

            }
        }
        return list
    }


}

private fun ArrayList<Pair<String, Int>>.toInt(): ArrayList<Int> {
    val list = arrayListOf<Int>()
    this.map {
        list.add(it.second)
    }
    return list
}

private fun ArrayList<Int>.swap(first: Int, last: Int) {
    val firstItem = this[first]
    this[first] = this[last]
    this[last] = firstItem
}