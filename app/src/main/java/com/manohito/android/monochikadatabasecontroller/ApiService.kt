package com.manohito.android.monochikadatabasecontroller

import com.manohito.android.monochikadatabasecontroller.model.Maker
import com.manohito.android.monochikadatabasecontroller.model.Shop
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import rx.Observable


interface ApiService {

    companion object {
        val API_BASE_URL = ""
    }

    @GET("shops.json")
    fun getShops(): Observable<MutableList<Shop>>

    @POST("shops.json")
    fun createShops(@Query("shop[name]") name: String,
                    @Query("shop[latitude]") latitude: Float,
                    @Query("shop[longitude]") longitude: Float,
                    @Query("shop[address]") address: String): Observable<Shop>

    @GET("shops/{id}.json")
    fun getShop(@Path("id") shopId: Int): Call<Shop>

    @DELETE("shops/{id}.json")
    fun deleteShop(@Path("id") shopId: Int): Observable<Shop>

    @GET("makers.json")
    fun getMakers(): Observable<List<Maker>>

    @GET("makers/{id}.json")
    fun getMaker(@Path("id") makerId: Int): Call<Maker>

    @DELETE("makers/{id}.json")
    fun deleteMaker(@Path("id") makerId: Int): Observable<Maker>
}