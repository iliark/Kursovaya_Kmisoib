import java.math.BigInteger
import java.util.*
class RSA(p: BigInteger, q: BigInteger) {
    private val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    private val n: BigInteger = p*q
    private val phi: BigInteger = (p- BigInteger.ONE)*(q- BigInteger.ONE)
    private var e = publicExp()
    private var d = e.modInverse(phi)
    fun getD(): BigInteger {
        return d
    }
    fun getE(): BigInteger {
        return e
    }
    private fun publicExp(): BigInteger {
        while (true) {
            val random = Random()
            val exponent = BigInteger(8, random)
            if (exponent > BigInteger.ONE && exponent < phi && exponent.gcd(phi)
                    .compareTo(BigInteger.ONE) == 0
            ) return exponent
        }
    }
    fun encrypt(text: String): List<BigInteger> {
        val result: MutableList<BigInteger> = ArrayList()
        for (i in text) {
            val code = BigInteger((alphabet.indexOf(i)+1).toString())
            result.add(code.modPow(e, n))
        }
        return result
    }

    fun decrypt(cipher: List<BigInteger>): List<BigInteger> {
        val result: MutableList<BigInteger> = ArrayList()
        for (symb in cipher) {
            result.add(symb.modPow(d, n))
        }
        return result
    }
}
