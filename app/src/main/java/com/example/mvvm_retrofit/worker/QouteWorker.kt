package com.example.mvvm_retrofit.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvm_retrofit.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QouteWorker(private val context: Context,params : WorkerParameters) : Worker(context,params) {
    companion object {
        var counter = 0
            private set

        fun incrementCounter() {
            counter++
        }
    }
    override fun doWork(): Result {
        incrementCounter()
        Log.d("Usman-Code","Worker Called $counter")
        val repository = (context as QuoteApplication).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQoutesBackground()
        }
        return Result.success()
    }


}