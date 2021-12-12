package aoc_12

import RunAoc
import utils.readInput

class Day : RunAoc {
    val input = ArrayList<String>(readInput(12))
    val small = emptySet<String>().toMutableSet()
    val big = emptySet<String>().toMutableSet()
    val hm = emptyMap<String, MutableSet<String>>().toMutableMap()
    var result = emptyList<MutableList<String>>().toMutableList()

    init {
        input.forEach {
            val route = it.split("-")
            route.forEach {
                when (it) {
                    it.lowercase() -> small.add(it)
                    else -> big.add(it)
                }
                if (!hm.containsKey(it)) hm.put(it, emptySet<String>().toMutableSet())
            }
            hm.getValue(route[0]).add(route[1])
            hm.set(route[0], hm.getValue(route[0]))
            hm.getValue(route[1]).add(route[0])
            hm.set(route[1], hm.getValue(route[1]))
        }
        for ((_, v) in hm) {
            if (v.contains("end")) {
                v.remove("end")
                v.add("end")
            }
        }
    }

    override fun a(): Int {
        val path = emptyList<String>().toMutableList()
        path.add("start")
        step("start", path, mutableSetOf<String>("start"))
        return result.size
    }

    private fun step(begin: String, path: MutableList<String>, smalled: MutableSet<String>) {
        for (i in hm.getValue(begin)) {
            val paths = emptyList<String>().toMutableList()
            val smalleds = emptySet<String>().toMutableSet()
            smalleds.addAll(smalled)
            paths.addAll(path)
            if (paths.contains("end")) {
                result.add(paths)
                break
            } else if (!smalleds.contains(i)) {
                if (i.lowercase().equals(i)) smalleds.add(i)
                paths.add(i)
                step(i, paths, smalleds)
            }

        }
    }

    private fun stepB(begin: String, path: MutableList<String>, smalled: MutableList<String>) {
        for (i in hm.getValue(begin)) {
            val paths = emptyList<String>().toMutableList()
            val smalleds = emptyList<String>().toMutableList()
            smalleds.addAll(smalled)
            paths.addAll(path)

            if (paths.contains("end")) {
                result.add(paths)
                break
            } else {
                if(!i.equals("start")) {
                    if (!smalleds.groupingBy { it }.eachCount().any { it.value == 2 } || !smalleds.contains(i)) {
                        if (i.lowercase().equals(i)) smalleds.add(i)
                        paths.add(i)
                        stepB(i, paths, smalleds)
                    }
                }
            }

        }
    }


    override fun b(): Int {
        result = emptyList<MutableList<String>>().toMutableList()
        val path = emptyList<String>().toMutableList()
        path.add("start")
        stepB("start", path, mutableListOf<String>("start"))
        return result.size
    }
}
