package aoc_2

import RunAoc
import java.io.File
import java.util.stream.Collectors

/**
 * Moving horizontal and depth
 */

class Day :RunAoc{
    val lines= File("aoc_2/input_a.txt")
        .readLines()
        .map { it.split(" ") }

    override fun a(): Int {

        val forward=lines.stream().filter { it.first().equals("forward")}
            .map { Integer.parseInt(it[1]) }
            .collect(Collectors.toList())
            .sum()
        val down=lines.stream().filter { it.first().equals("down")}
            .map { Integer.parseInt(it[1]) }
            .collect(Collectors.toList())
            .sum()
        val up=lines.stream().filter { it.first().equals("up")}
            .map { Integer.parseInt(it[1]) }
            .collect(Collectors.toList())
            .sum()

        return forward*(down-up)
    }

    override fun b():Int {
        var aim=0
        var depth=0
        var forward=0
        lines.forEach {
            val move=Integer.parseInt(it[1])
            when(it.first()){
                "down" -> {
                    aim+=move
                }
                "up" -> {
                    aim-=move
                }
                "forward" -> {
                    forward+=move
                    depth+=move*aim
                }

            }
        }

        return forward*depth
    }
}
