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
 *
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