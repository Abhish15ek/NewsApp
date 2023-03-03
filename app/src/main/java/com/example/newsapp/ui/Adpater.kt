package com.example.newsapp.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsapp.DetailActivity
import com.example.newsapp.R
import com.example.newsapp.Resource
import com.example.newsapp.model.NewsModel
import com.example.newsapp.network.NewsArticle

class Adapter(private val context: Context, private val items: List<NewsModel>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageView)
        val title: TextView = view.findViewById(R.id.textTitle)
        val author: TextView = view.findViewById(R.id.textAuthor)
        val date: TextView = view.findViewById(R.id.textPublishedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.first_screen, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
        Glide.with(context)
            .load(item.urlToImage)
            .into(holder.image)
            holder.title.text = item.title
            holder.author.text= item.author
            holder.date.text = item.publishedAt

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("title", item.title)
            intent.putExtra("author", item.author)
            intent.putExtra("date", item.publishedAt)
            intent.putExtra("content", item.content)
            intent.putExtra("urlToImage", item.urlToImage)
            intent.putExtra("url", item.url)

            context.startActivity(intent)

        }
    }

        override fun getItemCount(): Int {
            return items.size
        }

    }

