class GOST(){

    private val SUBSTITUTION_BLOCK = arrayOf(
        intArrayOf(1, 13, 4, 6, 7, 5, 14, 4),
        intArrayOf(15, 11, 11, 12, 13, 8, 11, 10),
        intArrayOf(13, 4, 10, 7, 10, 1, 4, 9),
        intArrayOf(0, 1, 0, 1, 1, 13, 12, 2),
        intArrayOf(5, 3, 7, 5, 0, 10, 6, 13),
        intArrayOf(7, 15, 2, 15, 8, 3, 13 ,8),
        intArrayOf(10, 5, 1, 13, 9, 4, 15, 0),
        intArrayOf(4, 9, 13, 8, 15, 2, 10, 14),
        intArrayOf(9, 0, 3, 4, 14, 14, 2, 6),
        intArrayOf(2, 10, 6, 10, 4, 15, 3, 11),
        intArrayOf(3, 14, 8, 9, 6, 12, 8, 1),
        intArrayOf(14, 7, 5, 14, 12, 7, 1, 12),
        intArrayOf(6, 6, 9, 0, 11, 6, 0, 7),
        intArrayOf(11, 8, 12, 3, 2, 0, 7, 15),
        intArrayOf(8, 2, 15, 11, 5, 9, 5, 5),
        intArrayOf(12, 12, 14, 2, 3, 11, 9, 3)
    )

    private fun xor2pow32(rightVal: String, key: String): String {
        val result = StringBuilder()
        val right = rightVal.toBigInteger(2)
        val key = key.toBigInteger(2)
        result.append((right+key).toString(2).substring(1,33))
        return result.toString()
    }
    private fun substitution(text: String, subBlock: Array<IntArray>): String{
        val result = StringBuilder()
        var k = 0
        var value: String
        val newtext = Transforms().toBlocks(text, 4)
        for (i in 0..7) {
            value = subBlock[newtext.substring(0 + k, 4 + k).toInt(2)][i].toString(2)
            if (value.length < 2) value = "000$value"
            if (value.length < 3) value = "00$value"
            if (value.length < 4) value = "0$value"
            result.append(value)
            k+=5
        }
        return result.toString()
    }
    fun encrypt(text: String, key:String){
        val binaryMessage = Transforms().textToBinary(text)
        val X0 = Transforms().textToBinary(key.substring(0,4))
        val L0 = binaryMessage.substring(0,32)
        val R0 = binaryMessage.substring(32,64)
        val sumR0X0 = xor2pow32(R0, X0)
        val substituted = substitution(sumR0X0, SUBSTITUTION_BLOCK)
        //println(substituted)
        val shifted = substituted.substring(11,32)+substituted.substring(0,11)
        //println("Сдвиг на 11 бит: $shifted")
        val R1 = Transforms().xor(L0, shifted)
        println("GOST L1: $R0")
        println("GOST R1: $R1")
    }

}