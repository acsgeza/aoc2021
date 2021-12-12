package aoc_4

import RunAoc
import utils.readInput
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

/**
 * Bingo
 */
class Day : RunAoc {

    val input = ArrayList<String>(readInput(4))
    val draws = input.removeAt(0).split(",").map { it.toInt() }
    val DIM = input.size / 6
    val aArray = Array(DIM, { Array(5, { Array(5, { 0 }) }) })

    /**
     * create boardArray
     */
    init {
        var board = 0
        while (input.isNotEmpty()) {
            input.removeAt(0)
            for (i in 0..4) {
                val row = input.removeAt(0)
                aArray[board][i] = row.trim().split("\\s+".toRegex()).map { it.toInt() }.toTypedArray()
            }
            board++
        }
    }

    /**
     *  if found -1 five times
     * @return true
     */
    fun checkBoard(board: Int): Boolean {
        for (row in 0..4) {
            val found = aArray[board][row].count { it == -1 }
            if (found == 5) return true
        }

        for (column in 0..4) {
            var foundInColumn = 0
            for (row in 0..4) {
                if (aArray[board][row][column] == -1) foundInColumn++
            }
            if (foundInColumn == 5) return true
        }
        return false
    }

    // If the number is drawn, mark with -1
    fun drawNext(board: Int, draw: Int): Boolean {
        for (row in 0..4) {
            for (column in 0..4) {
                aArray[board][row][column] = if (aArray[board][row][column] == draw) -1 else aArray[board][row][column]
            }
        }
        return checkBoard(board)
    }

    override fun a(): Int {
        for (draw in draws) {
            for (board in 0..DIM - 1) { // DIM = boards.size
                val win = drawNext(board, draw)
                if (win) {
                    return draw * reward(board)
                }
            }
        }
        return -1
    }

    /**
     * Sum the remains number on the board
     */
    private fun reward(board: Int): Int {
        var reward = 0
        for (row in 0..4)
            for (col in 0..4) {
                reward += if (aArray[board][row][col] != -1) aArray[board][row][col] else 0
            }
        return reward
    }

    /**
     * Search the last board
     */
    override fun b(): Int {
        val winners = mutableListOf<Int>()
        for (draw in draws) {
            for (board in 0..DIM - 1) {
                if (!winners.contains(board)) {
                    val win = drawNext(board, draw)
                    if (win) {
                        winners.add(board)
                        if(winners.size==DIM) return draw*reward(board)
                    }
                }
            }
        }
        return 0
    }
}
