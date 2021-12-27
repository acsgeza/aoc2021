package aoc_25

import RunAoc
import utils.readInput

class Day :RunAoc{
    val input = readInput(25)

    private val initialRegion = input.flatMapIndexed { y, row -> row.mapIndexed { x, c -> Point2D(x, y) to c } }
        .filterNot { it.second == '.' }
        .associate { it.first to it.second }

    private val xMax = input.first().length
    private val yMax = input.size

    data class Point2D(val x: Int, val y: Int)
    private fun Point2D.east() = Point2D((x + 1) % xMax, y)
    private fun Point2D.south() = Point2D(x, (y + 1) % yMax)

    override fun a(): Int {
        println(initialRegion)
        return generateSequence(initialRegion) { next(it) }
            .zipWithNext()
            .takeWhile { it.first != it.second }
            .count() + 1
    }

    override fun b():Int {
        return 0
    }

    private fun next(region: Map<Point2D, Char>) = region.toMutableMap().apply {
        filterValues { it == '>' }
            .keys
            .map { it to it.east() }
            .filter { it.second !in this }
            .forEach {
                remove(it.first)
                put(it.second, '>')
            }
        filterValues { it == 'v' }
            .keys
            .map { it to it.south() }
            .filter { it.second !in this }
            .forEach {
                remove(it.first)
                put(it.second, 'v')
            }
    }
}
