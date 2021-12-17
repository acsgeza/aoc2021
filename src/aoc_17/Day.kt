package aoc_17

import RunAoc
import utils.readInput
import utils.readText
import java.lang.Integer.max

class Day :RunAoc{
    val t = readText(17).split(",").map { it.trim().split("=")[1] }
        .map {it.split("..")}.map { it.map { it.toInt()} }.map { IntRange(it[0],it[1])}

    val targetArea = Rect(t[0], t[1])

    data class Rect(val xRange: IntRange, val yRange: IntRange) {
        operator fun contains(probe: Probe): Boolean = probe.x in xRange && probe.y in yRange

        operator fun compareTo(probe: Probe): Int {
            if (probe in this) return 0
            if (probe.x > xRange.last || probe.y < yRange.first) return -1
            return 1
        }
    }

    data class Probe(val x: Int, val y: Int, val t: Int, val dx: Int, val dy: Int) {
        fun step(): Probe = Probe(x + dx, y + dy, t + 1, maxOf(0, dx - 1), dy - 1)

        fun keepGoing(target: Rect): Boolean {
            if (this in target) return false
            if (target < this) return false
            if (dx == 0 && x !in target.xRange) return false
            return true
        }
    }

    override fun a(): Int {
        val highestValidYs = mutableSetOf<Int>()

        (1..125).forEach { candidateDx ->
            (1..1000).forEach { candidateDy ->
                var probe = Probe(0, 0, 0, candidateDx, candidateDy)
                var highestY = 0
                do {
                    probe = probe.step()
                    highestY = maxOf(highestY, probe.y)
                } while (probe.keepGoing(targetArea))
                if (probe in targetArea) {
                    highestValidYs += highestY
                }
            }
        }

        return highestValidYs.maxOf { it }
    }

    override fun b():Int {
        val solutions = mutableSetOf<Pair<Int, Int>>()

        (1..targetArea.xRange.last).forEach { candidateDx ->
            (targetArea.yRange.first..1000).forEach { candidateDy ->
                var probe = Probe(0, 0, 0, candidateDx, candidateDy)
                do {
                    probe = probe.step()
                } while (probe.keepGoing(targetArea))
                if (probe in targetArea) {
                    solutions.add(candidateDx to candidateDy)
                }
            }
        }

        return solutions.size
    }
}
