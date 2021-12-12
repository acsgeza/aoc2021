package aoc_10

import RunAoc
import utils.readInput
import java.math.BigInteger

/**
 * Opening and Closing Brackets
 */
data class Brackets(val ch: Char, var open: Int, var close: Int)
class Day : RunAoc {
    val input = ArrayList<String>(readInput(10))
    val points = hashMapOf<Char, Int>(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    val pointsB = hashMapOf<Char, Int>(')' to 1, ']' to 2, '}' to 3, '>' to 4)
    val reversed = hashMapOf<Char, Char>(')' to '(', ']' to '[', '}' to '{', '>' to '<')
    val opened = reversed.entries.associate { (k, v) -> v to k }

    override fun a(): Int {
        return input.map { getScoreInLine(it).second }.sum()
    }

    override fun b(): Int {
        val inComp = input.map { getScoreInLine(it).first }.filter { it.isNotEmpty() }
        val scores=inComp.map { val rev=it
            var score:BigInteger=BigInteger.ZERO
            while(rev.isNotEmpty()) {
                val c = rev.removeLast()
                score=score* BigInteger.valueOf(5)
                score += pointsB.getValue(opened.getValue(c)).toBigInteger()
            }
            score
        }.sorted()
        println(scores[scores.size/2])
        return 0
    }

    private fun getScoreInLine(str: String): Pair<MutableList<Char>, Int> {
        val lsOpen = emptyList<Char>().toMutableList()
        str.forEach {
            if (it in opened.keys) {
                lsOpen.add(it)
            } else {
                if (lsOpen.last() == reversed.getValue(it)) lsOpen.removeLast()
                else
                    return emptyList<Char>().toMutableList() to points.getValue(it)
            }
        }
        return lsOpen to 0
    }
}
