package aoc_7

import RunAoc
import utils.readStringAsInts
import kotlin.math.abs
import kotlin.math.floor

/**
 * Minimal distance and fuel (Median vs. Mean)
 */

class Day :RunAoc{
    val input = readStringAsInts(7)
    fun median(l: List<Int>) = l.sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }

    override fun a(): Int {
        val c=median(input)
        return input.map { abs(c-it) }.sum()
    }


    override fun b():Int {
        val c= floor(input.average()).toInt()
        return input.map { IntRange(1,abs(c -it)).sum() }.sum()
    }
}
