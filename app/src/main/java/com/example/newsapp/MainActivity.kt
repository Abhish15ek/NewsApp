package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.newsapp.viewmodel.NewsViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.database.NewsDB
import com.example.newsapp.model.NewsModel
import com.example.newsapp.network.NewsArticle
import com.example.newsapp.repository.NewsRepo
import com.example.newsapp.ui.Adapter
import com.example.newsapp.viewmodel.NewsViewModelProviderFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var finalData: List<NewsModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myApp = applicationContext as NewsApplication
        val newsRepo = NewsRepo(myApp.database)

        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
         viewModel.getSavedNewsfromDB().observe(this, Observer {
             Log.d("abhi" , "observer executed")
         finalData = it
         adaptercall() })
    }

    private fun adaptercall() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = Adapter(this, finalData)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        adapter.notifyDataSetChanged()
    }
}
