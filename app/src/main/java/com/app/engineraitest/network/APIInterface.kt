package com.app.engineraitest.network

import com.app.engineraitest.data.ClsPost
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST



interface APIInterface {

    @GET("search_by_date")
    fun getPosts(@Query("tags") tags: String, @Query("page") page: String) : Call<ClsPost>
}