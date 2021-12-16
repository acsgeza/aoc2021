package `try`

import `try`.domain.Generator
import `try`.domain.Second

fun main() {
    Generator.work()
    Generator().otherFun()
    Generator.Solo.alive()
    Generator.Inner().inner()
    Generator.Inner.auto()
    Second.exec()

}