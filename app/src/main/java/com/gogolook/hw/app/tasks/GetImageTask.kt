package com.gogolook.hw.app.tasks

import com.gogolook.hw.app.data.ApiGetImageModel
import com.gogolook.hw.app.utils.OkHttpUtils
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object GetImageTask {

    const val poixabayURL = "https://pixabay.com/api/"
    const val apiKey = "31005795-8db8e625ea1f12b305a75cbe1"
    const val type = "photo"

    fun get(searchString:String,callback : GetImageTaskInterface){
        val url = buildQueryString(searchString)
        val request = Request.Builder().also {
            it.url(url).get()
        }.build()
        OkHttpUtils.instance?.getMethod(request,object : Callback{
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val responseString = response.body()?.string()
                val responseModel = Gson().fromJson<ApiGetImageModel>(responseString,ApiGetImageModel::class.java)
                callback.updateImageData(responseModel)
            }
        })
    }

    fun get(searchString:String): String? {
        val url = buildQueryString(searchString)
        val request = Request.Builder().also {
            it.url(url).get()
        }.build()
        return OkHttpUtils.instance?.getMethod(request)
    }

    fun buildQueryString(input:String):String{
        val keysArray = input.split(" ")
        var keysString = ""
        for (i in keysArray.indices){
            val key = keysArray[i]
            if(i == 0){
                keysString = key
            }else{
                keysString += "+$key"
            }
        }
        return "$poixabayURL?key=$apiKey&q=${keysString}&image_type=$type&per_page=200"
    }

    interface GetImageTaskInterface{
        fun updateImageData(data:ApiGetImageModel)
    }
}