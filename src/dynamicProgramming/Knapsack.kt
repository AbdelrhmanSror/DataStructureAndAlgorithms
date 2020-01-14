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
    val knapsack = Knapsack(5)
    knapsack.addItem(Item(1, 60))
    knapsack.addItem(Item(2, 100))
    knapsack.addItem(Item(3, 120))
    knapsack.calculateBestItemCanFit()


}


data class Item(val weight: Int, val cost: Int)

/**
 * u can not use fraction of item
 *  With the dynamic-programming solution, you either take the item or not.
 *  There’s no way for it to figure out that you should take half an item.
 *  u could use greedy algorithm instead
 */
class Knapsack(private val sizeOfKnapsack: Int) {
    //map represent the knapsack sub problem size with item
    /**
     * for me i think using hash map in this case is more suitable than using 2d array
     * because in evey step i just need the current one and previous one
     * but with 2d array i always keep unuseful info in memory even that i do not need it any more
     */
    private val itemMap = LinkedHashMap<Int, Item>()
    private var prvItemMap = LinkedHashMap<Int, Item>()
    private val listOfItems = LinkedList<Item>()
    private var leastWeight = Int.MAX_VALUE
    fun addItem(item: Item) {
        /**
        only add valid item where its weight is less than or equal the[sizeOfKnapsack]
        find the least weight so we can construct our grid table
         */
        if (item.weight in 0..sizeOfKnapsack) {
            listOfItems.add(item)
            if (item.weight < leastWeight)
                leastWeight = item.weight

        }
    }

    fun calculateBestItemCanFit() {
        //iterate over each list of item to update the itemTable
        listOfItems.forEach { currentItem ->
            for (weight in leastWeight..sizeOfKnapsack) {

                if (!itemMap.containsKey(weight)) {
                    itemMap[weight] = Item(currentItem.weight, currentItem.cost)
                } else {
                    val remainingWeight = weight - currentItem.weight
                    //get the current item value while iterating over columns of table which has its key as weight it can hold
                    val currentItemValue = currentItem.cost + (prvItemMap[remainingWeight]?.cost ?: 0)
                    //comparing the current value with old value which was in the that pocket
                    if (currentItem.weight <= weight && currentItemValue > prvItemMap[weight]!!.cost) {
                        //updating the value in the pocket
                        itemMap[weight] = Item(weight, currentItemValue)
                    }
                }
            }
            prvItemMap.putAll(itemMap)

        }

        println(listOfItems)
        print(itemMap)
    }

}
