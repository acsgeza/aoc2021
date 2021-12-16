package utils

import java.io.File
import java.time.LocalDate

fun readText(day: Int)=File("aoc_$day","input_a.txt").readText()
fun readInput(day:Int)= File("aoc_$day","input_a.txt").readLines()
fun readInput(day:Int,filename:String)= File("aoc_$day",filename).readLines()
fun readInputAsInts(day:Int)= File("aoc_$day","input_a.txt").readLines().map {it.toInt()}
fun readInputCharsAsInts(day:Int)= File("aoc_$day","input_a.txt").readLines().map {it.toCharArray().map {it.digitToInt()}}.toMutableList()
fun readStringAsInts(day:Int)= File("aoc_$day","input_a.txt").readText().trim().split(",").map {it.toInt()}
fun readInputAsInts(day:Int,filename:String)= File("aoc_$day",filename).readLines().map {it.toInt()}
