package com.example.newsapp.repository

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
                        article.title,
                            article.author ?: "a",
                            article.description ?: "a",
                                       article.url,
                            article.urlToImage ?: "a",
                        article.publishedAt,
                            article.content ?: "b"
                    ))
                }
            }
        }
        return response
    }

//    suspend fun getAllNews(country: String, pageNumber: Int) =
//        NewsService.newsInstance.getEverthing(q, pageNumber)

//    suspend fun saveNews(article: NewsModel) = database.newsDao().insertNews(article)

    fun getSavedNews() = database.newsDao().getNewsFromDB()

//    suspend fun deleteNews(article: NewsModel) = database.newsDao().deleteNews(article)
}




