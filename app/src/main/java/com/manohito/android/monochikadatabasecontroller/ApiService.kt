package com.manohito.android.monochikadatabasecontroller

import com.manohito.android.monochikadatabasecontroller.model.Maker
import com.manohito.android.monochikadatabasecontroller.model.Shop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable


interface ApiService {

    companion object {
        val API_BASE_URL = ""
    }

    @GET("shops.json")
    fun getShops(): Observable<List<Shop>>

    @GET("shops/{id}.json")
    fun getShop(@Path("id") shopId: Int): Call<Shop>

    @GET("makers.json")
    fun getMakers(): Observable<List<Maker>>

    @GET("makers/{id}.json")
    fun getMaker(@Path("id") makerId: Int): Call<Maker>
}