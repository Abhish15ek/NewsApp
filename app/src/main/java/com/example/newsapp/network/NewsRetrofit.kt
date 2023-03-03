package com.example.newsapp.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY ="92dd2363daf24f668b72b0733d577f20"
interface NewsInterface {
    @GET("everything")
    suspend fun getHeadlines(@Query("q") q: String = "apple",@Query("page")
    pageNumber: Int = 1, @Query("apikey") apikey: String = API_KEY) : Response<NewsArticle>
// Check call on it
//    @GET("everything")
//    suspend fun getEverthing(@Query("q") country: String = "in",@Query("page")
//pageNumber: Int = 1, @Query("apikey") apikey: String = API_KEY) : Response<NewsArticle>
}

object NewsService{
    private var loggingInterceptor = HttpLoggingInterceptor()
    init {
        loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(
            moshi)).build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}
