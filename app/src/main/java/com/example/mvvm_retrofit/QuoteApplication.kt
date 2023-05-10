package com.example.mvvm_retrofit

import android.app.Application
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.mvvm_retrofit.api.QuoteService
import com.example.mvvm_retrofit.api.RetrofitHelper
import com.example.mvvm_retrofit.db.QuoteDatabase
import com.example.mvvm_retrofit.repository.QouteRepository
import com.example.mvvm_retrofit.worker.QouteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication:Application() {

    lateinit var quoteRepository: QouteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(QouteWorker::class.java,15,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
//        val workerRequest2 = OneTimeWorkRequest.Builder(QouteWorker::class.java)
//            .setConstraints(constraints)
//            .build()
//        WorkManager.getInstance(this).enqueue(workerRequest2)
    }

    private fun initialize(){
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QouteRepository(quoteService,database,applicationContext)

    }
}