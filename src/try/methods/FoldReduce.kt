package `try`.methods

import kotlin.test.assertEquals

fun main() {
    val numbers: List<Int> = listOf(1, 2, 3)
    val reversed = numbers.foldRight(listOf<Int>()) { next, acc -> acc + next }
    assertEquals(listOf(3,2,1), reversed)
    val sum: Int = numbers.foldRight(0){ acc, next -> acc + next }
    println(sum)
    numbers.let { println(it)}

    val nestedList= listOf(5..6,6..7,8..9)
    val resmap=nestedList.flatMap { x-> x }
    println(resmap)
   val diceOutcomes = (1..3).flatMap { x -> (1..3).flatMap { y -> (1..3).map { z -> x + y + z } } }
        .groupingBy { it }
        .eachCount().toMutableMap()
    val newOuts=diceOutcomes.map { (forward, freq) -> forward to freq }
    println(newOuts)

}