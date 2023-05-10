package com.example.mvvm_retrofit.db

import androidx.room.*
import com.example.mvvm_retrofit.models.Result

@Dao
interface QuoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes: List<com.example.mvvm_retrofit.models.Result>)

    @Update
    suspend fun updateQuotes(quotes: List<com.example.mvvm_retrofit.models.Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes():List<Result>
}