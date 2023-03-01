package com.example.newsapp.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.model.NewsModel


@Dao
interface NewsDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertNews(newsModel: NewsModel)

   @Query("SELECT * FROM NewsItem")
   fun getNewsFromDB(): LiveData<List<NewsModel>>

   @Delete
   suspend fun deleteNews(newsModel: NewsModel)
}
