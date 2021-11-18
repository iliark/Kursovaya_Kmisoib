import java.math.BigInteger
class DigitalSignature(p: BigInteger, q: BigInteger, h0: BigInteger) {
    private val hash = Hash(p, q, h0)
    private val rsa = RSA(p, q)
    private val m = p * q
    fun sign(text: String): BigInteger {
        println("Подпись: ${hash.getHash(text).modPow(rsa.D(), m)}")
        return hash.getHash(text).modPow(rsa.D(), m)
    }
    fun check(signed: BigInteger): BigInteger {
        println("Проверка подписи: ${signed.modPow(rsa.E(), m)}")
        return signed.modPow(rsa.E(), m)
    }
}