package com.example.mvvm_retrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_retrofit.models.Result

@Database(entities = [com.example.mvvm_retrofit.models.Result::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao():QuoteDAO

    // Thread Safe Approach
    companion object{
        // Whenever our instance value assigned its updated value will be available to all threads
        @Volatile
        private var INSTANCE:QuoteDatabase? = null

        fun getDatabase(context: Context):QuoteDatabase{
            if(INSTANCE == null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context, QuoteDatabase::class.java, "quoteDB"
                    ).build()
                }

            }
            return INSTANCE!!
        }
    }

}