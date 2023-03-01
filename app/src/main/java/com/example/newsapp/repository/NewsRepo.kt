package com.example.newsapp.repository

import android.util.Log
import com.example.newsapp.database.NewsDB
import com.example.newsapp.model.NewsModel
import com.example.newsapp.network.NewsArticle
import com.example.newsapp.network.NewsService
import retrofit2.Response



class NewsRepo(private val database: NewsDB) {


    suspend fun getHeadlines(country: String, pageNumber: Int) : Response<NewsArticle>
    { val response =
        NewsService.newsInstance.getHeadlines(country, pageNumber)
        if(response.isSuccessful) {
            val art = response.body()?.articles
            if(art!= null)
            {
                for (article in art)
                {
                    database.newsDao().insertNews(NewsModel(
                            article?.title ?: "a",
                            article?.author ?: "a",
                            article?.description ?: "a",
                            article?.url ?: "a",
                            article?.urlToImage ?: "a",
                            article?.publishedAt ?: "a",
                            article?.content ?: "b"
                    ))
                }
            }
        }
        return response
    }

    suspend fun getAllNews(country: String, pageNumber: Int) =
        NewsService.newsInstance.getEverthing(country, pageNumber)

//    suspend fun saveNews(article: NewsModel) = database.newsDao().insertNews(article)

    fun getSavedNews() = database.newsDao().getNewsFromDB()

    suspend fun deleteNews(article: NewsModel) = database.newsDao().deleteNews(article)
}



//
//    suspend fun getAllNews() {
//        val response = newsInstance.getHeadlines()
//        if (response.isSuccessful) {
//            val articles = response.body()?.articles
//            var cnt = 0
//            if (articles != null) {
//                for (article in articles) {
//                    database.newsDao().insertNews(
//                        NewsModel(
//                            cnt,
//                            article?.title ?: "a",
//                            article?.author ?: "a",
//                            article?.description ?: "a",
//                            article?.url ?: "a",
//                            article?.urlToImage ?: "a",
//                            article?.publishedAt ?: "a",
//                            article?.content ?: "b"
//                        )
//                    )
//                    cnt++
//                }
//
//
//            } else {
//                Log.d("abhi", "API REQEUEST FAILED ")
//            }
//
//        }
//    }



