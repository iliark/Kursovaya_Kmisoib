import java.math.BigInteger
class Hash(private val p: BigInteger, private val q: BigInteger, private val h0: BigInteger) {
    private val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    private fun hashFunction(x: BigInteger): BigInteger {
        return x.modPow(BigInteger.valueOf(2), p.multiply(q))
    }
    fun getHash(text: String): BigInteger {
        var hash = h0
        for (i in text) {
            val code: Int = alphabet.indexOf(i)+1
            hash = hashFunction(hash.add(BigInteger.valueOf(code.toLong())))
        }
        return hash
    }
}