package com.example.mvvm_retrofit.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_retrofit.api.QuoteService
import com.example.mvvm_retrofit.db.QuoteDatabase
import com.example.mvvm_retrofit.models.Result
import com.example.mvvm_retrofit.utils.NetworkUtils

class QouteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLiveData = MutableLiveData<List<Result>>()

    val quotes:LiveData<List<Result>>
    get() = quotesLiveData

    suspend fun getQuotes(page:Int){

        if(NetworkUtils.checkForInternet(applicationContext)){
            val result = quoteService.getOuotes(page)
            if(result?.body() != null){
//                if(quoteDatabase.quoteDao().getQuotes().size > 0) {
//                    quoteDatabase.quoteDao().updateQuotes(result.body()!!.results)
//                }
//                else{
//                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
//                }
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body()!!.results)

            }
        }
        else{
            val quotes = quoteDatabase.quoteDao().getQuotes()
            quotesLiveData.postValue(quotes)
        }

    }

    suspend fun getQoutesBackground(){
        val randomNumer  = (Math.random()+ 10).toInt()
        val result = quoteService.getOuotes(randomNumer)
        if(result?.body() != null){
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
            val list = mutableListOf<Result>()
            list.addAll(quotesLiveData.value ?: emptyList())
            list.addAll(result.body()?.results ?: emptyList())
            quotesLiveData.postValue(list)
        }
    }
}