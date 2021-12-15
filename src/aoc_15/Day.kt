package aoc_15

import RunAoc
import utils.readInputCharsAsInts
import java.util.*

class Day : RunAoc {
    val input = readInputCharsAsInts(15)
    val H = input.size
    val W = input[0].size
    val risks: IntArray

    init {
        risks = IntArray(H * W) { input[it / W][it % W] }
    }

    private fun solve(width: Int, height: Int, risks: IntArray): Int {
        val bests = IntArray(risks.size) { Int.MAX_VALUE }
        bests[0] = 0
        val queue = PriorityQueue(compareBy(Pair<Int, Int>::first))
        queue.add(0 to 0)
        while (true) {
            val i = queue.remove().second
            val c0 = bests[i]
            if (i == risks.lastIndex) return c0
            val x0 = i % width
            val y0 = i / width
            for ((x1, y1) in arrayOf(x0 - 1 to y0, x0 to y0 - 1, x0 to y0 + 1, x0 + 1 to y0)) {
                if (x1 !in 0 until width || y1 !in 0 until height) continue
                val j = y1 * width + x1
                val c1 = c0 + risks[j]
                if (c1 < bests[j]) {
                    bests[j] = c1
                    queue.add(c1 to j)
                }
            }
        }
    }

    override fun a(): Int {
        return solve(W, H, risks)
    }

    override fun b(): Int {
        return solve(5 * W,
        5 * H,
        IntArray(25 * risks.size) {
            val x = it % (5 * W)
            val y = it / (5 * W)
            (risks[y % (risks.size / W) * W + x % W] - 1 + x / W + y / H) % 9 + 1
        })

    }
}
