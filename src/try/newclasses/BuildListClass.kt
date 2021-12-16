package `try`.newclasses

val x = listOf('b', 'c')
fun main() {
    val b= buildList {
        add("almafa")
        add(x)
        add(7)
        add(Alma("jonatán"))
    }
    println(b)
    val nested= buildList {
        add(9)
        add(b)
    }
    println(nested)
    val y = buildList<Alma> {
        for(i in 0..2)
       Alma.getAlma().also { add(it) }
    }
    println(y)

    var samename=8
    samename= run {
        val samename=7
        println(samename)
        samename
    }+2
    println(samename)

    var person = Person("Anupam", "Kotlin")
    println(person)
    var l = person.let { it.tutorial = "Android1" }
    println(person)
    var al = person.also { it.tutorial = "Android2" }

    println(l)
    println(al)
    println(person)
    person.apply { println("ok")
        val a="alama"
        name=a
    }
    println(person)

}
data class Person(var name: String, var tutorial : String)

class Alma(val bits: String) {
    override fun toString(): String {
        return "Alma(bits='$bits')"
    }
    companion object ONE{
        fun getAlma():Alma{
            return Alma("barack")
        }
    }

}
