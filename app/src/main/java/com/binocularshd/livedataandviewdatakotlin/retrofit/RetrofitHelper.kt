package com.binocularshd.livedataandviewdatakotlin.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper{

    val baseUrl="https://themuslim360.com/api/"
    fun getInstance(): Retrofit{


        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }

}