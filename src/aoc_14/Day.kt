package aoc_14

import RunAoc
import utils.readInput
import java.math.BigInteger

class Day : RunAoc {
    val input = ArrayList<String>(readInput(14))
    val rules = input.drop(2).map {
        val rule = it.split(" -> ")
        rule[0] to rule[1][0]
    }.toMap()

    fun <T> MutableMap<T, BigInteger>.inc(k: T, i: BigInteger = BigInteger.ONE) = set(k, get(k)?.plus(i) ?: i)
    fun <T> MutableMap<T, BigInteger>.dec(k: T, i: BigInteger) = set(k, get(k)?.minus(i) ?: error(""))

    private fun step(days:Int): BigInteger {

        val charFrequency=input[0].groupingBy { it }.eachCount().map { (k,v) -> k to v.toBigInteger()}.toMap().toMutableMap()
        var pairFrequency=input[0].windowed(2).map { "" + it[0] + it[1]}
            .groupingBy { it }.eachCount().map { (k,v) -> k to v.toBigInteger()}.toMap()

        for (i in 0 until days) {
            val stepFrequency = mutableMapOf<String, BigInteger>()
            for ((pair, freq) in pairFrequency.entries) {
                stepFrequency.inc("" + pair[0] + rules.getValue(pair), freq)
                stepFrequency.inc("" + rules.getValue(pair) + pair[1], freq)
                charFrequency.inc(rules.getValue(pair), freq)
            }
            pairFrequency = stepFrequency
        }
        val max = charFrequency.values.maxOrNull()!!
        val min = charFrequency.values.minOrNull()!!
        println(charFrequency)
        return max.minus(min)
    }

    override fun a(): Int {
        println(step(10))
        return 0
    }

    override fun b(): Int {
        println(step(40))
        return 0
    }

}
