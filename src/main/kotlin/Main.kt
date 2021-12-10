import java.math.BigInteger
import java.security.DigestInputStream

fun main() {
    DES().encrypt("АРКАДЬЕВ", "ДМИТРИЕВ")
    GOST().encrypt("АРКАДЬЕВ", "ОРТОхлорбензилиденмалонодинитрил")
    val rsa =  RSA(13.toBigInteger(), 5.toBigInteger())
    val rsaencrypted = rsa.encrypt("АИД")
    println("Результат шифрования RSA: $rsaencrypted")
    println("Результат дешифрования RSA: ${rsa.decrypt(rsaencrypted)}")
    val hash = Hash(13.toBigInteger(), 5.toBigInteger(), 2.toBigInteger()).getHash("АРКАДЬЕВ")
    println("Результат хэширования: $hash")
    val DiSi = DigitalSignature(19.toBigInteger(), 13.toBigInteger(), Hash(13.toBigInteger(), 5.toBigInteger(), 2.toBigInteger()))
    val signed = DiSi.sign("АРКАДЬЕВ")
    DiSi.check(signed)
}
