package com.voduchuy.rxjava

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private var apiInterface: ApiInterface? = null
        fun getApiInterface(): ApiInterface {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

            val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
            apiInterface = retrofit.create(ApiInterface::class.java)
            return apiInterface!!

        }
    }

}