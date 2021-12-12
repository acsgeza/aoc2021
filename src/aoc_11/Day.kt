package aoc_11

import RunAoc
import java.io.File
import java.util.Collections.addAll

/**
 * Reborn and populate
 */

class Day : RunAoc {
    private lateinit var r: List<MutableList<Int>>
    val input= File("aoc_11","input_a.txt").readLines().map { it.trim().toCharArray().map {it.digitToInt()}}
    val colsize=input[0].size
    var flash=0
    override fun a(): Int {
        @Suppress("UNCHECKED_CAST")
        r=  input.map { it.map { it } } as List<MutableList<Int>>
        for(i in 1..100) reborn()
        return flash
    }

    override fun b(): Int {
        @Suppress("UNCHECKED_CAST")
        r=  input.map { it.map { it } } as List<MutableList<Int>>
        var step=0
        while(r.any { it.sum()>0 }){
            reborn()
            step++
        }
        return step
    }

    private fun reborn() {
        var row = 0
        input.forEach {
            var col = 0
            it.forEach {
                r[row][col] +=1
                col++
            }
            row++
        }

        while(r.filter { it.filter{ it==10}.size>0}.size>0) {
            populateOne()
        }
        setZero()
    }

    private fun populateOne() {
        var row = 0
        input.forEach {
            var col = 0
            it.forEach {
                if (r[row][col] == 10) {
                    for (i in row - 1..row + 1)
                        if (i != -1 && i != input.size) {
                            for (j in col - 1..col + 1)
                                if (j != -1 && j != colsize) {
                                    if (r[i][j] <10 ) r[i][j] += 1
                                }
                        }
                r[row][col]=11
                flash++
                }
                col++
            }
            row++
        }
    }

    private fun setZero(){
        var row = 0
        input.forEach {
            var col = 0
            it.forEach {
                if (r[row][col] == 11) r[row][col] = 0
                col++
            }
            row++
        }
    }
}

