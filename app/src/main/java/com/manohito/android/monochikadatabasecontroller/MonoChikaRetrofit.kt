package com.manohito.android.monochikadatabasecontroller

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MonoChikaRetrofit {

    companion object {
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(ApiService.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
        }

        fun getApi() = retrofit.create(ApiService::class.java)
    }
}