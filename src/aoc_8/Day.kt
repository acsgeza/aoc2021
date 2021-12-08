package aoc_8

import RunAoc
import utils.readInput

class Day : RunAoc {
    val input = ArrayList<String>(readInput(8))
    override fun a(): Int {
        var count = 0
        input.map { it.split("|") }.map { it[1].trim().split(" ") }
            .forEach {
                it.forEach {
                    when (it.length) {
                        2, 3, 4, 7 -> count++
                    }
                }
            }

        return count
    }

    override fun b(): Int {
        val dec = input.map { it.split("|") }.map {
            val digits = Array<Set<Char>>(10, { emptySet<Char>().toSet() })
            it[0].trim().split(" ").sortedBy { it.length }.forEach {
                val actSet = it.toCharArray().toSet()
                when (it.length) {
                    2 -> digits[1] = actSet
                    3 -> digits[7] = actSet
                    4 -> digits[4] = actSet
                    5 -> {

                        if (digits[2].isEmpty()) {
                            digits[2] = actSet
                        } else if (digits[3].isEmpty()) {
                            digits[3] = actSet
                        } else if (digits[5].isEmpty()) {
                            digits[5] = actSet
                        }
                    }
                    6 -> {
                        if (digits[0].isEmpty()) {
                            digits[0] = actSet
                        } else if (digits[6].isEmpty()) {
                            digits[6] = actSet
                        } else if (digits[9].isEmpty()) {
                            digits[9] = actSet
                        }
                    }
                    7 -> digits[8] = actSet
                }

            }

            val top = digits[7] - digits[1]
            val bottom = (digits[2].intersect(digits[3]).intersect(digits[5]) - top).toMutableSet()
            val middle = bottom.intersect(digits[4])
            bottom.removeAll(middle)
            val bleft = digits[8] - digits[4] - top - bottom
            val mvert = bottom + top + middle
            val tleft = digits[8] - mvert - bleft - digits[1]
            val elem6 =
                arrayListOf<Set<Char>>(digits[0], digits[6], digits[9]).filter { it.intersect(digits[1]).size == 1 }
            digits[6] = elem6[0] as MutableSet<Char>
            digits[9] = digits[8] - bleft
            digits[3] = mvert + digits[1]
            digits[0] = digits[8] - middle
            val tright = digits[8] - digits[6]
            val bright = digits[1] - tright
            digits[5] = digits[6] - bleft
            digits[2] = digits[8] - tleft - bright
            digits[3] = mvert + digits[1]

            val sc=it[1].trim().split(" ")
                .map {
                    var res = -1
                    val item = it.toCharArray().toSet()
                    for (i in 0..9) {

                        if (item.size == digits[i].size) {
                            if ((it.toCharArray().toSet() - digits[i]).size == 0) {
                                res = i
                                break
                            }
                        }
                    }
                    res.toString()
                }.joinToString(separator = "")
                sc.toInt()
        }

        return dec.sum()
    }
}
