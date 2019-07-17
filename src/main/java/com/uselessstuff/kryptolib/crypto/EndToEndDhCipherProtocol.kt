package com.uselessstuff.kryptolib.crypto

import java.math.BigInteger

class EndToEndDhCipherProtocol : CipherProtocol {

    fun publicKey(p: BigInteger, g: BigInteger, secret: BigInteger): BigInteger = g.modPow(secret, p)

    fun privateKey(publicKey: BigInteger, secret: BigInteger, p: BigInteger) = publicKey.modPow(secret, p)
}