package com.uselessstuff.kryptolib.crypto

import com.uselessstuff.kryptolib.error.OneTimePadCharsetException
import com.uselessstuff.kryptolib.error.OneTimePadLengthException
import java.io.UnsupportedEncodingException
import kotlin.experimental.xor

class OneTimePadCipherProvider : CipherProvider {

    private val charset = Charsets.UTF_8

    @Throws(OneTimePadLengthException::class)
    override fun encrypt(key: ByteArray, data: ByteArray): ByteArray {
        checkKeyAndData(key, data)
        return encryptData(key, data).apply {
            key.clear()
            data.clear()
        }
    }

    @Throws(OneTimePadLengthException::class, OneTimePadCharsetException::class)
    override fun encrypt(key: ByteArray, data: String): ByteArray {
        try {
            checkKeyAndData(key, data.toByteArray())
        } catch (e: UnsupportedEncodingException) {
            throw OneTimePadCharsetException()
        }
        return data.toByteArray().let { dataArray ->
            encryptData(key, dataArray).apply {
                key.clear()
                dataArray.clear()
            }
        }
    }

    @Throws(OneTimePadLengthException::class)
    override fun decrypt(key: ByteArray, encrypted: ByteArray): ByteArray {
        checkKeyAndData(key, encrypted)
        return decryptData(key, encrypted).apply {
            key.clear()
            encrypted.clear()
        }
    }

    @Throws(OneTimePadLengthException::class)
    override fun decryptString(key: ByteArray, encrypted: ByteArray): String {
        checkKeyAndData(key, encrypted)
        return String(decryptData(key, encrypted), charset).apply {
            key.clear()
            encrypted.clear()
        }
    }

    private fun encryptData(key: ByteArray, data: ByteArray): ByteArray =
            ByteArray(data.size).apply {
                for (i in key.indices) {
                    this[i] = data[i] xor key[i]
                }
            }

    private fun decryptData(key: ByteArray, encrypted: ByteArray): ByteArray =
            ByteArray(key.size).apply {
                for (i in key.indices) {
                    this[i] = encrypted[i] xor key[i]
                }
            }

    @Throws(OneTimePadLengthException::class)
    private fun checkKeyAndData(key: ByteArray, data: ByteArray) {
        if (key.size != data.size) throw OneTimePadLengthException()
    }
}