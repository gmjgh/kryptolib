package com.uselessstuff.kryptolib.crypto

interface CipherProvider {

    fun encrypt(key: ByteArray, data: ByteArray): ByteArray

    fun encrypt(key: ByteArray, data: String): ByteArray

    fun decrypt(key: ByteArray, encrypted: ByteArray): ByteArray

    fun decryptString(key: ByteArray, encrypted: ByteArray): String

    fun ByteArray.clear() = fill(0)

}