package com.gogolook.hw.app.utils

import okhttp3.OkHttpClient
import okhttp3.Callback
import okhttp3.Request
import java.util.concurrent.TimeUnit

internal class OkHttpUtils {
    private val timeOutSec = 5

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(timeOutSec.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(timeOutSec.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(timeOutSec.toLong(), TimeUnit.SECONDS)
            return builder.build()
        }

    companion object {
        private var mOkHttpUtils: OkHttpUtils? = null
        val instance: OkHttpUtils?
            get() {
                if (mOkHttpUtils == null) {
                    synchronized(OkHttpUtils::class.java) {
                        if (mOkHttpUtils == null) {
                            mOkHttpUtils = OkHttpUtils()
                        }
                    }
                }
                return mOkHttpUtils
            }
    }

    fun getMethod(request: Request, callback: Callback) {
        val getCall = client.newCall(request)
        getCall.enqueue(callback)
    }

    fun getMethod(request: Request): String? {
        val getCall = client.newCall(request)
        return getCall.execute().body()?.string()
    }
}