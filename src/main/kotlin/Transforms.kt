class Transforms {

    fun xor (rightVal: String, key: String): String {
        val result = BooleanArray(rightVal.length)
        val right = toBoolean(rightVal)
        val key = toBoolean(key)
        for (i in result.indices)
            result[i] = right[i] xor (key[i])
        return boolToDigits(result)
    }
     fun toBlocks(text: String, size: Int): String{
        val blocks= StringBuilder()
        for (i in text.indices)
            if (i%size == 0 && i>0) blocks.append(" ").append(text[i])
            else blocks.append(text[i])
        return blocks.toString()
    }

    fun textToBinary(text: String): String {
        val charToDigits = StringBuilder()
        for (i in text.indices) {
            if (text[i] == '_') charToDigits.append("0${(text[i].code).toString(2)}")
            else charToDigits.append((text[i].code - 848).toString(2))
        }
        return charToDigits.toString()
    }

    fun toBoolean(text: String): BooleanArray{
        val newBool = BooleanArray(text.length)
        for (i in text.indices)
            newBool[i] = text[i] == '1'
        return  newBool
    }
    fun boolToDigits(boolAr: BooleanArray): String{
        val newDigCode = StringBuilder()
        for (i in boolAr.indices)
            if (boolAr[i]) newDigCode.append('1') else newDigCode.append('0')
        return newDigCode.toString()
    }

}