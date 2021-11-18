class DES{

    private val INITIAL_PERMUTATION_TABLE =
        intArrayOf(
            58, 50, 42, 34, 26, 18, 10, 2,
            60, 52, 44, 36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6,
            64, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1,
            59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7,
        )
    private val EXPANSION_TABLE =
        intArrayOf(
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1,
        )
    private val S_TABLE = arrayOf(
        intArrayOf(
            14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
            0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
            4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
            15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13
        ), intArrayOf(
            15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
            3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
            0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
            13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9
        ), intArrayOf(
            10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
            13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
            13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
            1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12
        ), intArrayOf(
            7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
            13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
            10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
            3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14
        ), intArrayOf(
            2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
            14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
            4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
            11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3
        ), intArrayOf(
            12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
            10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
            9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
            4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13
        ), intArrayOf(
            4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
            13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
            1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
            6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12
        ), intArrayOf(
            13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
            1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
            7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
            2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11
        )
    )

    private val P_TABLE = intArrayOf(
        16, 7, 20, 21,
        29, 12, 28, 17,
        1, 15, 23, 26,
        5, 18, 31, 10,
        2, 8, 24, 14,
        32, 27, 3, 9,
        19, 13, 30, 6,
        22, 11, 4, 25
    )
    private val REVERSE_PERMUTATION = intArrayOf(
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9,  49, 17, 57, 25,)


    private fun permutation(text: String, permTable: IntArray): String {
        val newOrder = StringBuilder()
        for (i in text.indices) {
            newOrder.append(text[permTable[i]-1])
        }
        return newOrder.toString()
    }

    private fun expandR0(text: String): String {
        val newOrder = StringBuilder()
        for (i in 0..47) {
            newOrder.append(text[EXPANSION_TABLE[i]-1])
        }
        return newOrder.toString()

    }

    private fun roundKeyGen(key: String): String {
        val newKey = StringBuilder(key)
        delete(newKey)
        delete(newKey)
        newKey.delete(newKey.length - 1, newKey.length)
        return newKey.toString()
    }

    private fun delete(key: StringBuilder) {
        var i = key.length - 1
        while (i > 0) {
            key.delete(i, i + 1)
            i -= 8
        }
    }
    private fun STransition(text: String, table: Array<IntArray>, blockSize:Int): String {
        val result = StringBuilder()
        val newText = Transforms().toBlocks(text, blockSize)
        val tableSize = table.size
        var k = 0
        var value: String
        var row:Int
        var column: Int
        for (i in 0 until tableSize){
            row = (newText[0+k] + newText[5+k].toString()).toInt(2)
            column = newText.substring(1+k,5+k).toInt(2)
            value = table[i][column+16*row].toString(2)
            if (value.length < 2) value = "000$value"
            if (value.length < 3) value = "00$value"
            if (value.length < 4) value = "0$value"
            result.append(value)
            k+=blockSize+1
        }
        return result.toString()
    }
    fun encrypt(text: String, key:String){
        val binaryMessage = Transforms().textToBinary(text)
        val binKey = Transforms().textToBinary(key)
        val binaryPermutation = permutation(binaryMessage, INITIAL_PERMUTATION_TABLE)
        //println("Перестановка исходного ообщения: $binaryPermutation")
        val L0 = binaryPermutation.substring(0,32)
        val R0 = binaryPermutation.substring(32,64)
        //println("L0 = $L0")
        //println("R0 = $R0")
        val expandedR0 = expandR0(R0)
        //println("Расширение правого подблока до 48 бит: $expandedR0")
        val roundKey = roundKeyGen(binKey)
        //println("Ключ раунда: $roundKey")
        val whiten = Transforms().xor(expandedR0, roundKey)
        //println("Суммирование по модулю 2: $whiten")
        val stransited = STransition(whiten, S_TABLE, 6)
        val newR0 = permutation(stransited, P_TABLE)
        val newSum = Transforms().xor(newR0, L0)
        //println("Поразрядное суммирование: $newSum")
        val L1R1 = R0+newSum
        //println("Результат конкатенации: $L1R1")
        val result = permutation(L1R1, REVERSE_PERMUTATION)
        println("Результат первого раунда шифрования алгоритмом DES: $result")
    }

}