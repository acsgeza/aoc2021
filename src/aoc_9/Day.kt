package aoc_9

import RunAoc
import utils.BorderedTable
import utils.readInput

class Day : RunAoc {
    val input = ArrayList<String>(readInput(9))
    val cols = input[0].length
    val r = BorderedTable.getIntTable(input, 9)
    private fun process(cols: Int, board: List<MutableList<Int>>): List<Int> {
        val f = mutableListOf<Int>()
        for (i in 1..cols) {
            val elem = board[1][i]
            if (elem < board[1][i - 1]) // left
                if (elem < board[1][i + 1]) //right
                    if (elem < board[0][i]) //up
                        if (elem < board[2][i]) //down
                            f.add(elem)
        }
        return f
    }

    override fun a(): Int {
        val result = r.windowed(3).map {
            process(cols, it)
        }.map { it.sum() + it.size }.sum()

        return result
    }

    private fun go(i: Int, j: Int): Int {
        var find = 0
        if (r[i][j] != 9) {
            find++
            r[i][j] = 9
            if (r[i - 1][j] < 9) find += go(i - 1, j)
            if (r[i][j + 1] < 9) find += go(i, j + 1)
            if (r[i][j - 1] < 9) find += go(i, j - 1)
            if (r[i + 1][j] < 9) find += go(i + 1, j)
        }
        return find
    }

    override fun b(): Int {
        val res = step().sortedDescending().take(3).reduce { acc, i -> acc * i }
        return res
    }

    private fun step(): MutableList<Int> {
        val findings = emptyList<Int>().toMutableList()
        for (i in 1..input.size) {
            for (j in 1..cols) {
                val found = go(i, j)
                if (found > 0) {
                    findings.add(found)
                }

            }
        }
        return findings
    }
}
