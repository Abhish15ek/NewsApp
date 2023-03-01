package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.dao.NewsDao
import com.example.newsapp.model.NewsModel


@Database(entities = [NewsModel::class], version = 1)
abstract class NewsDB : RoomDatabase() {
    abstract fun newsDao(): NewsDao
// companion is used to make singleton object means singleinstance
    companion object{
        @Volatile
        private var INSTANCE:NewsDB?= null
        fun getDB(context: Context): NewsDB{
            if(INSTANCE != null)
            {
                return INSTANCE!!
            }
            synchronized(this)
            {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, NewsDB::class.java, "DBName"
                ).fallbackToDestructiveMigration().build()
                return INSTANCE!!
            }

        }
    }
}



