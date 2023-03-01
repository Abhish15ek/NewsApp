package com.example.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Resource
import com.example.newsapp.model.NewsModel
import com.example.newsapp.network.NewsArticle
import com.example.newsapp.repository.NewsRepo
import kotlinx.coroutines.launch


class NewsViewModel(private var newsRepo: NewsRepo) : ViewModel() {
   val breakingNews: MutableLiveData<Resource<NewsArticle>> = MutableLiveData()
    var breakingNewsPage = 1
  var breakingNewsResponse: NewsArticle? = null

  init {
      getBreakingNews("in")
  }

  fun getBreakingNews(country: String) = viewModelScope.launch {
    breakingNews.postValue(Resource.Loading())
    val response = newsRepo.getHeadlines(country, breakingNewsPage)
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


//  fun saveArticle(article: NewsModel) = viewModelScope.launch {
//    newsRepo.save(article)
//  }

  fun getSavedNewsfromDB() = newsRepo.getSavedNews()

  fun deleteArticle(article: NewsModel) = viewModelScope.launch {
    newsRepo.deleteNews(article)
  }
    //this is original till before line init
//    private val newsLiveData: MutableLiveData<List<NewsArticle>> = MutableLiveData()
//    fun initrepo(db: NewsDB) {
//        newsRepo = NewsRepo(db)
//        Log.d("abhi", "init")
//    }
//
//    fun call_api() {
//        viewModelScope.launch {
//            Log.d("abhi", "init__")
//            newsRepo.getAllNews()
//
//        }
//    }
//    init {
//        // Initialize newsLiveData with initial data
//        GlobalScope.launch {
//            newsLiveData.value = newsRepo.getAllNews()
//        }

    }
//    fun getNewsLiveData(): LiveData<List<NewsArticle>> {
//        // Return the LiveData object for observation by the UI
//        return newsLiveData
//    }



