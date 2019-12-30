import jdk.jfr.Unsigned

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