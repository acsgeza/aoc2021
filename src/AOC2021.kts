import java.time.LocalDate
import kotlin.reflect.full.createInstance

val TODAY = true
val runday= if (TODAY) LocalDate.now() else LocalDate.parse("2021-12-19")
val day =runday.dayOfMonth
println("day: $day")
val className=Class.forName("aoc_${day}.Day")

var aoc_Day= className.kotlin.createInstance()

val a = aoc_Day.javaClass.getMethod("a")
println("A:"+a.invoke(aoc_Day))

val b = aoc_Day.javaClass.getMethod("b")
println("B:"+b.invoke(aoc_Day))
