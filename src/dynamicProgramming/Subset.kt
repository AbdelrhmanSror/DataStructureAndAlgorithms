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
import kotlin.collections.LinkedHashMap


fun main() {
    val subset = Subset(6)
    subset.addItem(1)
    subset.addItem(2)
    subset.addItem(3)
    subset.addItem(4)
    subset.addItem(5)
    subset.addItem(6)

    print("${subset.calculateAllSetCanAddUp()}")
}

/**
 * u can not use fraction of item
 *  With the dynamic-programming solution, you either take the item or not.
 *  There’s no way for it to figure out that you should take half an item.
 *  u could use greedy algorithm instead
 *  find all subset that can add up to [n]
 */

class Subset(private val n: Int) {
    /**
     * for me i think using hash map in this case is more suitable than using 2d array
     * because in evey step i just need the current one and previous one
     * but with 2d array i always keep unuseful info in memory even that i do not need it any more
     */
    private val currentNumberRow = LinkedHashMap<Int, Int>()
    private val prvNumberRow = LinkedHashMap<Int, Int>()
    private val listOfNumbers = LinkedList<Int>()
    private var leasNumber = Int.MAX_VALUE
    fun addItem(number: Int) {
        /**
        only add valid item where its weight is less than or equal the[n]
        find the least number so we can construct our grid table
         */
        if (number in 0..n) {
            listOfNumbers.add(number)
            if (number < leasNumber)
                leasNumber = number

        }
    }

    fun calculateAllSetCanAddUp(): Int {
        var numberOfSubset = 0
        //iterate over each list of number to update the number table
        listOfNumbers.forEach { currentNumber ->
            for (number in leasNumber..n) {
                /**
                 * if the pocket does not exist yet table i create it
                 * here unlike knapsack i do not maximize or minimize the value of each pocket,
                 * all i care about if there are numbers that can be summed to fit this pocket
                 * otherwise i set this pocket with 0 which mean that we can not come up with numbers that can be summed to fit this pocket
                 */
                if (!currentNumberRow.containsKey(number)) {
                    if (currentNumber == number) currentNumberRow[number] = currentNumber
                    else currentNumberRow[number] = 0

                } else {
                    val remainingNumberSpace = number - currentNumber
                    //get the current item value while iterating over columns of table which has its key as weight it can hold
                    val currentPocketValue = currentNumber + (prvNumberRow[remainingNumberSpace] ?: 0)
                    //comparing the current value with old value which was in the that pocket
                    if (currentPocketValue == number) {
                        //updating the value in the pocket
                        currentNumberRow[number] = currentPocketValue
                    } else {
                        currentNumberRow[number] = prvNumberRow[number]!!

                    }

                }
                /**
                 *if the current pocket of n contains number =the pocket value that means we have a subset that can be add up to [n]
                 */
                if (number == n && currentNumberRow[number] == n)
                    numberOfSubset++
            }
            //set current row as prev row to use it with the next row
            prvNumberRow.putAll(currentNumberRow)


        }

        return numberOfSubset
    }

}
