package com.abdiel.e_commerce.api

import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    //Login
    @FormUrlEncoded
    @POST("api/auth/login")
    suspend fun login(
        @Field("phone_number") phone_number: String?,
        @Field("password") password: String?
    ) : String

    //Logout
    @POST("api/auth/logout")
    suspend fun logout(
    ): String

    //ListById
    @GET("api/product/{id}")
    suspend fun getProductById(
    ): String

    //GetListAll
    @GET("api/product")
    suspend fun getAllProduct(
    ): String

    //GetListAll
    @GET("api/profile")
    suspend fun getProfile(
    ): String

    //AddToCart
    @FormUrlEncoded
    @POST("api/chart")
    suspend fun addToCart(
        @Field("size_id") size_id: Int?,
        @Field("qty") qty: Int?
    ) : String

    //Checkout
    @FormUrlEncoded
    @POST("api/checkout")
    suspend fun checkout(
        @Field("alamat") alamat: String?,
        @Field("provinsi") provinsi: String?,
        @Field("kota") kota: String?,
        @Field("kecamatan") kecamatan: String?,
        @Field("notes") notes: String?
    ) : String

    //EditCart
    @FormUrlEncoded
    @POST(" api/chart/edit/{id}")
    suspend fun editCart(
        @Field("qty") qty: Int?
    ) : String

    @POST("note/{id}")
    suspend fun deleteNote(
        @Path("id") id: String
    ): String

}