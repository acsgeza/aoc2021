package aoc_13

import RunAoc
import utils.readInput

/**
 * Folding problem
 */
class Day : RunAoc {
    val input = ArrayList<String>(readInput(13))
    val coords = input.filter {
        if (it.length > 0) it[0].isDigit() else false
    }
    val folds = input.filter {
        if (it.length > 0) !it[0].isDigit() else false
    }.map {
        val tuple = it.split("=")
        tuple[0].last() to tuple[1].toInt()
    }
    val whole = coords.map {
        val tuple = it.split(",")
        tuple[0].toInt() to tuple[1].toInt()
    }

    override fun a(): Int {
        val firstFold=folds.take(1).toMutableList()
        val result = nextFold(firstFold)
        return result.size
    }

    override fun b(): Int {
        val allfolds=folds.toMutableList()
        val result = nextFold(allfolds)
        val xmax=result.maxOf { it.first }
        val ymax=result.maxOf { it.second }
        for(i in 0..ymax){
            for (j in 0..xmax)
            {
                val act=Pair(j,i)
                if(result.any {it==act}) print("#") else print(".")
            }
            println("")
        }
        return 0
    }

    private fun nextFold(folds:MutableList<Pair<Char,Int>>): Set<Pair<Int, Int>> {
        var res = whole.toSet()
        while (folds.isNotEmpty()) {
            val fold = folds[0]
            folds.removeFirst()
            res = res.map {
                when (fold.first) {
                    'y' -> if (it.second > fold.second) it.first to 2 * fold.second - it.second else it
                    else -> if (it.first > fold.second) 2 * fold.second - it.first to it.second else it
                }
            }.toSet()
        }
        return res
    }
}
