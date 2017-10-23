package com.manohito.android.monochikadatabasecontroller

import com.manohito.android.monochikadatabasecontroller.model.MainCategory
import com.manohito.android.monochikadatabasecontroller.model.Maker
import com.manohito.android.monochikadatabasecontroller.model.Shop
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import rx.Observable


interface MonoChikaService {

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

    @DELETE("shops/{id}.json")
    fun deleteShop(@Path("id") shopId: Int): Observable<Shop>

    @GET("makers.json")
    fun getMakers(): Observable<List<Maker>>

    @POST("makers.json")
    fun createMaker(@Query("maker[name]") name: String): Observable<Maker>

    @DELETE("makers/{id}.json")
    fun deleteMaker(@Path("id") makerId: Int): Observable<Maker>

    @GET("main_categories.json")
    fun getMainCategories(): Observable<List<MainCategory>>

    @POST("main_categories.json")
    fun createMainCategory(@Query("main_category[name]") name: String): Observable<MainCategory>

    @DELETE("main_categories/{id}.json")
    fun deleteMainCategory(@Path("id") mainCategoryId: Int): Observable<MainCategory>
}