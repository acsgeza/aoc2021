package aoc_24

import RunAoc
import utils.readInput

class Day :RunAoc{
    val input = readInput(24)
    data class StackItem(val digitIndex: Int, val addend: Int)

    data class Parameters(val xAddend: Int, val yAddend: Int)

    private val parameters = input.chunked(18).map { line ->
        Parameters(
            line[5].substringAfterLast(" ").toInt(),
            line[15].substringAfterLast(" ").toInt()
        )
    }
    override fun a(): Int {
        println(findModelNumber(largest = true))
        return 0
    }

    override fun b():Int {
        println(findModelNumber(largest = false))
        return 0
    }

    private fun findModelNumber(largest: Boolean): Long {
        val stack = ArrayDeque<StackItem>()
        val digits = Array(14) { 0 }
        parameters.forEachIndexed { digitIndex, parameters ->
            if (parameters.xAddend >= 10) {
                stack.add(StackItem(digitIndex, parameters.yAddend))
            } else {
                val popped = stack.removeLast()
                val addend = popped.addend + parameters.xAddend
                val digit = (if (largest) 9 downTo 1 else 1..9).first { it + addend in 1..9 }
                digits[popped.digitIndex] = digit
                digits[digitIndex] = digit + addend
            }
        }
        return digits.fold(0L) { acc, d -> acc * 10 + d }
    }
}
