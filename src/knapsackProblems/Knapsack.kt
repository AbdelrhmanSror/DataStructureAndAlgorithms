package knapsackProblems

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
     * BAD: use 2d array instead
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
        prvItemMap.putAll(itemMap)
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
