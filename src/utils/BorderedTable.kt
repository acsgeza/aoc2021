package utils

class BorderedTable() {
    companion object TableHelper{
        fun getIntTable(inputStringTable: List<String>, FILL: Int): List<MutableList<Int>> {
            val cols = inputStringTable[0].length
            val inputInt = inputStringTable.map {
                it.map {
                    it.digitToInt()
                }
            }
            val c = inputInt[0].toMutableList()

            for (i in 0..cols - 1) {
                c[i] = FILL
            }

            val extendUP = arrayListOf(c) + inputInt + arrayListOf(c)

            return extendUP.map {
                @Suppress("NAME_SHADOWING")
                val c = it.toMutableList()
                c.add(0, FILL)
                c.add(FILL)
                c
            }
        }
    }
}