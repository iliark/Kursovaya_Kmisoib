import java.math.BigInteger
class DigitalSignature(p: BigInteger, q: BigInteger, val hash: Hash) {
    private val rsa = RSA(p, q)
    private val m = p * q
    fun sign(text: String): BigInteger {
        println("Подпись: ${hash.getHash(text).modPow(rsa.getD(), m)}")
        return hash.getHash(text).modPow(rsa.getD(), m)
    }
    fun check(signed: BigInteger): BigInteger {
        println("Проверка подписи: ${signed.modPow(rsa.getE(), m)}")
        return signed.modPow(rsa.getE(), m)
    }
}