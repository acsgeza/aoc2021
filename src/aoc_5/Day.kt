package aoc_5

import RunAoc
import utils.readInput
import java.lang.Math.abs

/**
 * Overlapping lines in Table
 */

data class Point(var x:Int, var y:Int){
    companion object PointHelper {
        fun getPoint( coord:String): Point {
            val pointCoord=coord.trim().split(",")
            return Point(pointCoord[0].toInt(),pointCoord[1].toInt())
        }
    }
}

data class Line(val start:Point,val stop:Point){

    companion object LineHelper {
        fun getPoints( line:Line,part2:Boolean=false): List<Point> {
            val points=ArrayList<Point>()
            if(line.start.x ==line.stop.x){
                var begin=line.start.y
                var end=line.stop.y
                begin = if(begin>end)  end.also { end = begin } else begin
                for (i in begin..end){
                    points.add(Point(line.start.x,i))
                }
            }
            if(line.start.y ==line.stop.y){
                var begin=line.start.x
                var end=line.stop.x
                begin = if(begin>end)  end.also { end = begin } else begin
                for (i in begin..end){
                    points.add(Point(i,line.start.y))
                }
            }
            if(part2){
                val beginX = line.start.x
                val endX = line.stop.x
                val beginY = line.start.y
                val endY = line.stop.y
                val dirX=endX-beginX
                val dirY=endY-beginY
                if(abs(dirX)==abs(dirY)){
                    for(i in 0..abs(dirX)){
                        points.add(Point(beginX+i*dirX/abs(dirX),beginY+i*dirY/ abs(dirY)))
                    }
               }
            }
            return points
        }
    }
}

class Day : RunAoc {
    val input = ArrayList<String>(readInput(5))
    var lines=ArrayList<Line>()
    init {
        input.asSequence().map {
            it.split("->")
        }.forEach {
            lines.add(Line(Point.getPoint(it[0]), Point.getPoint(it[1])))
        }
    }
    override fun a(): Int {
        val used=ArrayList<Point>()

        lines.asSequence().forEach { used.addAll(Line.getPoints(it)) }
        val founds=used.asSequence().groupingBy { it }.eachCount().filter { it.value >1 }.count()
        return founds
    }

    override fun b(): Int {
        val used=ArrayList<Point>()

        lines.asSequence().forEach { used.addAll(Line.getPoints(it,true)) }
        val founds=used.asSequence().groupingBy { it }.eachCount().filter { it.value >1 }.count()
        return founds
    }
}
