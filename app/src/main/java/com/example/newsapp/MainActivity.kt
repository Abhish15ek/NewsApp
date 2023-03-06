package com.example.newsapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import com.example.newsapp.viewmodel.NewsViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.model.NewsModel
import com.example.newsapp.repository.NewsRepo
import com.example.newsapp.ui.Adapter
import com.example.newsapp.viewmodel.NewsViewModelProviderFactory


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var finalData: List<NewsModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

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
//        adapter.notifyDataSetChanged()
    }



}
