package com.example.newsapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton

class DetailActivity: AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_screen)

        val titleNews: TextView = findViewById(R.id.titleView)
        val authorNews: TextView = findViewById(R.id.authorView)
        val dateNews: TextView = findViewById(R.id.dateView)
        val contentNews: TextView = findViewById(R.id.contentView)
        val imageNews: ImageView= findViewById(R.id.imageView)

        val bundle: Bundle?= intent.extras
        val title1 =bundle!!.getString("title")
        val author1 = bundle.getString("author")
        val date1 = bundle.getString("date")
        val content1 = bundle.getString("content")
        val imageURl = bundle.getString("urlToImage")

       titleNews.text= title1
        authorNews.text= author1
        dateNews.text=Utils().dateFormat(date1.toString())
        contentNews.text=content1
       Glide.with(imageNews).load(imageURl).into(imageNews)


//Back Button
        val backButton = findViewById<ImageButton>(R.id.buttonBack)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}