package `try`.domain

class Generator {

    companion object Worker {
        var r = IntRange(4, 7)
        private fun next(x: Int) = if (r.last == x) r.first else x % r.last + 1
        var start=6

        fun work() {
            Solo.alive()
            println(start)
            for (i in 0..5) {
                start = next(start)
                println(start)
            }
        }
    }

    object Solo {
        fun alive(){
            println("I am alive")
        }
    }

    class Inner {
        companion object Auto {
            fun auto(){
                println("auto")
            }
        }
        fun inner(){
            println("INNER")
        }
    }

    fun otherFun(){
        println("other fun")
    }
}

class Second {
    companion object Work{
        fun exec(){
            println("Second")
        }
    }
}

