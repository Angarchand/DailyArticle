package com.binocularshd.livedataandviewdatakotlin.retrofit

import com.binocularshd.livedataandviewdatakotlin.adpater.DailyArticleAdapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {
    @GET("latest-articles")
suspend fun getDailyArticles():Response<DailyArticleResponce>

@GET("article/{slug}")
suspend fun readArticle(@Path("slug")slug:String):Response<ReadArticleResponse>

}