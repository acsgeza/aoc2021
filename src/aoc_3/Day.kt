package aoc_3

import RunAoc
import utils.readInput

class Day :RunAoc{
    val DIM=12
    val input=readInput(3)
    val bits=Array(DIM) { 0 }
    override fun a(): Int {
        input.forEach {
            for(i in 0..DIM-1) {
                bits[i] += it[i].digitToInt()
            }
        }
       val gArray=bits.map { if(it>(input.size-it)) '1' else '0'}
       val eArray=bits.map { if(it<(input.size-it)) '1' else '0'}
        val gamma=String(gArray.toCharArray()).toInt(2)
        val epsilon=String(eArray.toCharArray()).toInt(2)

        return gamma*epsilon
    }
    enum class Bool(val value:Int){
        OXYGEN(1),
        CO2(0);
    }
    fun filtered( filteredList:List<String>,pos:Int,rating:Bool): List<String> {
        val one=filteredList.filter { it[pos].digitToInt()==rating.value}.count()
        val more = one>filteredList.size-one
        val choose= if(more) 1 else if(one==filteredList.size-one) rating.value else 0
        return filteredList.filter { choose == it[pos].digitToInt() }
    }

    fun getRating(filteredList:List<String>,rating: Bool):Int{
        var newList=filteredList
        for( i in 0..DIM-1) {
            newList = filtered(newList, i,rating)
            if (newList.size==1) break
        }
        return newList[0].toInt(2)
    }
    override fun b():Int {
        return getRating(input,Bool.OXYGEN)*getRating(input,Bool.CO2)
    }
}
