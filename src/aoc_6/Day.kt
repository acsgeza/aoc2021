package aoc_6

import RunAoc
import java.io.File
import java.math.BigInteger

class Day :RunAoc{
    val file = File("aoc_6","input_a.txt").readText().trim()
    var input=file.split(",").map {it.toInt()}.toMutableList()
    var ZERO=BigInteger.ZERO
    val NAP = 256
    override fun a(): Int {
        for(i in 1..2) nextDay()
        return input.size
    }

    private fun nextDay() {
        val newCreated = ArrayList<Int>()
        input = input.asSequence().map {
            it - 1
        }.map { if (it == -1 || it == -11) 6 else it }.toMutableList()
        input.forEach {
            if (it == 6) newCreated.add(-2) }
        input.addAll(newCreated)
    }

    override fun b(): Int {
        val map=input.groupingBy { it }.eachCount()
        var nextMap =map.map { (key,value) ->  key to value.toBigInteger()}.toMap()
        for(i in 1..NAP) nextMap = nextDay2(nextMap)
        val sum= nextMap.values.sumOf { it }
        println("RESULT: $sum")
        return 0
    }

    private fun nextDay2(map: Map<Int,BigInteger>): HashMap<Int, BigInteger> {
        val newMap=HashMap<Int,BigInteger>()
        for(i in -11..6){
            newMap.set(i , map.getOrDefault(i+1,ZERO))
        }
        intArrayOf(-11,-1).forEach{
            newMap.set(-2, newMap.getOrDefault(-2, ZERO) + newMap.getOrDefault(it, ZERO)) //new
            newMap.set(6, newMap.getOrDefault(6, ZERO) + newMap.getOrDefault(it, ZERO)) // reborn
            newMap.set(it,ZERO) //remove
        }
        return newMap
    }
}
