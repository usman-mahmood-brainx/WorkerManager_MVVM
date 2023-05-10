package com.example.mvvm_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_retrofit.api.QuoteService
import com.example.mvvm_retrofit.api.RetrofitHelper
import com.example.mvvm_retrofit.databinding.ActivityMainBinding
import com.example.mvvm_retrofit.models.Result
import com.example.mvvm_retrofit.repository.QouteRepository
import com.example.mvvm_retrofit.viewmodels.MainViewModel
import com.example.mvvm_retrofit.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var quoteList : MutableList<Result>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        quoteList = mutableListOf()
        val adapter = QuotePagingAdapter(quoteList)
        binding.rvQuoteList.layoutManager = LinearLayoutManager(this)
        binding.rvQuoteList.adapter = adapter


        mainViewModel.quotes.observe(this, Observer {
            quoteList.clear()
            quoteList.addAll(it.reversed())
            adapter.notifyDataSetChanged()
            Log.d("Usman Data",it.toString())
            Toast.makeText(this,it.size.toString(),Toast.LENGTH_SHORT).show()
        })

        
    }
}