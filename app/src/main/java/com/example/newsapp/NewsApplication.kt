package com.example.newsapp

import android.app.Application
import android.util.Log
import com.example.newsapp.database.NewsDB

class NewsApplication: Application() {

    val database: NewsDB by lazy {NewsDB.getDB(this)}
    //debug
    override fun onCreate() {
        super.onCreate()
        Log.d("NewsApplication", "Running")
    }

}