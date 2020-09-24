package com.example.newsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.squareup.picasso.Picasso

class NewsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var list = mutableListOf<Article>()

    fun updateData(newList: List<Article>?) {
        list = newList as MutableList<Article>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    class ViewHolder(
        itemView: View
    ) :
        RecyclerView.ViewHolder(itemView) {

        private var image: ImageView? = null
        private var img: ImageView? = null
        private var title: TextView? = null
        private var name: TextView? = null
        private var date: TextView? = null
        private var author: TextView? = null
        private var description: TextView? = null


        init {
            image = itemView.findViewById(R.id.image)
            img = itemView.findViewById(R.id.img_click)
            name = itemView.findViewById(R.id.tv_name)
            title = itemView.findViewById(R.id.tv_title)
            date = itemView.findViewById(R.id.tv_date)
            author = itemView.findViewById(R.id.tv_author)
//            description = itemView.findViewById(R.id.description)
        }

        fun bind(
            article: Article,
            onItemClickListener: OnItemClickListener
        ) {
            Log.e(
                "TAG",
                "bind: desc ${article.description} source ${article.source} " + article.url + "${article.urlToImage}"
            )
            when {
                article != null -> {
                    if (article.urlToImage != null)
                        Picasso.get().load(article.urlToImage).fit()
                            .placeholder(R.drawable.emptyimg)
                            .into(image)
                    title?.text = article.title
                    date?.text = article.publishedAt
                    author?.text = article.author
                    name?.text = article.source?.name
                    img?.setOnClickListener {
                        onItemClickListener.onItemClick(article)
                    }

                }
            }

        }

    }

    interface OnItemClickListener {
        fun onItemClick(model: Article)
    }
}
