package com.example.parliamentapp.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/**
 * Name: Yana Krylova
 * Student Number: 2113602
 * Description: Fetching information about parliament members
 */

private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

//create an instance of Moshi
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//create an instance of retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("mps.json")
    suspend fun getMemberList(): List<Parliament>
}

object ParliamentApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java) }
}