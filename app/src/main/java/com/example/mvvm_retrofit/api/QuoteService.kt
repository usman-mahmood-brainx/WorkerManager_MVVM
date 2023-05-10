package com.example.mvvm_retrofit.api

import com.example.mvvm_retrofit.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    suspend fun getOuotes(@Query("page") page:Int):Response<QuoteList>
}