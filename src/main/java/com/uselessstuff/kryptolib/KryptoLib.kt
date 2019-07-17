package com.uselessstuff.kryptolib

import android.annotation.SuppressLint
import android.content.Context
import com.uselessstuff.kryptolib.error.KryptoLibNotInitializedException

class KryptoLib private constructor() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: KryptoLib? = null

        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        public fun init(context: Context) {
            KryptoLib.context = context
            instance = KryptoLib()
        }

        public fun getInstance(): KryptoLib = instance
            ?: throw KryptoLibNotInitializedException()

    }




}