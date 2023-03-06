package com.example.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Resource
import com.example.newsapp.network.NewsArticle
import com.example.newsapp.repository.NewsRepo
import kotlinx.coroutines.launch


class NewsViewModel(private var newsRepo: NewsRepo) : ViewModel() {
   private val breakingNews: MutableLiveData<Resource<NewsArticle>> = MutableLiveData()
    private var breakingNewsPage = 1
  private var breakingNewsResponse: NewsArticle? = null

  init {
      getBreakingNews("apple")
  }

  private fun getBreakingNews(q: String) = viewModelScope.launch {
//    breakingNews.postValue(Resource.Loading())
    val response = newsRepo.getHeadlines(q, breakingNewsPage)
    breakingNews.postValue(handleBreakingNewsResponse(response))
  }

  private fun handleBreakingNewsResponse(response: retrofit2.Response<NewsArticle>): Resource<NewsArticle> {
    if (response.isSuccessful) {
      response.body()?.let { resultResponse ->
        breakingNewsPage++
        if (breakingNewsResponse == null) {
          breakingNewsResponse = resultResponse
        } else {
          val oldArticles = breakingNewsResponse?.articles
          val newArticles = resultResponse.articles
          oldArticles?.addAll(newArticles)
        }
        return Resource.Success(breakingNewsResponse ?: resultResponse)
      }
    }
    return Resource.Error(response.message())
  }


  fun getSavedNewsfromDB() = newsRepo.getSavedNews()

    }



