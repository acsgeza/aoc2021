package `try`.methods

fun main() {
    val input="100010100001011011001010100000000001101010000000000000101111010001111000"
    println(input)
    val s=input.drop(6).chunked(5)
        .let { println(it)
            it.takeWhile { g -> g.first() == '1' } +
            it.first { g -> g.first() == '1' } }
    println(s)
}
