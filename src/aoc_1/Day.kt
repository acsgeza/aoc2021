package aoc_1

import RunAoc
import java.io.File
import java.util.*


class Day :RunAoc{

    val init_memory:MutableList<Int> by lazy {resetMemory()}
    private fun resetMemory(): MutableList<Int> {

        val sc= Scanner(File("aoc_1/input_a.txt")).useDelimiter("\\n")
        val ls = mutableListOf<Int>()
        while(sc.hasNextInt()){
            ls.add(sc.nextInt())
        }
        return ls
    }

    override fun a():Int {
        val sc= Scanner(File("aoc_1/input_a.txt")).useDelimiter("\\n")
        var counter = 0
        var prev = Int.MAX_VALUE
        while(sc.hasNextInt()){
            val next = sc.nextInt()
           if(next>prev){
               counter++
           }
            prev=next
        }
        return counter
    }


    override fun b():Int {
        val ls= mutableListOf<Int>().apply { addAll(init_memory) }
        var counter=0
        for ((index, value) in ls.withIndex()) {
            if(index>2) {
              if(value > ls[index - 3]){
                  counter++
              }
            }
        }
        return counter
    }
}
