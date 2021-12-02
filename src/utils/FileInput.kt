package utils

import java.io.File
import java.time.LocalDate

fun readInput(day:Int)= File("aoc_$day","input_a.txt").readLines()
fun readInputAsInts(day:Int)= File("aoc_$day","input_a.txt").readLines().map {it.toInt()}
