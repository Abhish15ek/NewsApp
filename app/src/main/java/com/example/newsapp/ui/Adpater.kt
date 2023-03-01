package com.example.newsapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.Resource
import com.example.newsapp.model.NewsModel
import com.example.newsapp.network.NewsArticle

class Adapter(private val context: Context, private val items: List<NewsModel>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.textTitle)
        val description: TextView = view.findViewById(R.id.textAuthor)
        val date: TextView = view.findViewById(R.id.textPublishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.first_screen, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        //       holder.image.setImageDrawable()= item.
        holder.title.text = item.title
        holder.description.text =item.description
        holder.date.text =item.publishedAt

//       holder.image.setImageDrawable()= item.
    }

    override fun getItemCount(): Int {
       return items.size
    }

}
