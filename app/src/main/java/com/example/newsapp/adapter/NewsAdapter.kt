package com.example.newsapp.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.example.newsapp.utils.Utils
import com.example.newsapp.utils.Utils.randomDrawbleColor

class NewsAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var list = mutableListOf<Article>()

    fun updateData(newList: List<Article>?) {
        list.addAll(newList!!)
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
        private var time: TextView? = null
        private var source: TextView? = null
        private var author: TextView? = null
        private var progressBar: ProgressBar? = null
        private var description: TextView? = null


        init {
            image = itemView.findViewById(R.id.image)
            img = itemView.findViewById(R.id.img_click)
            name = itemView.findViewById(R.id.tv_name)
            title = itemView.findViewById(R.id.tv_title)
            date = itemView.findViewById(R.id.publishedAt)
            author = itemView.findViewById(R.id.tv_author)
            time = itemView.findViewById(R.id.tv_time)
            progressBar = itemView.findViewById(R.id.progress_load_photo)
            description = itemView.findViewById(R.id.desk)
        }

        @SuppressLint("SetTextI18n")
        fun bind(
            article: Article,
            onItemClickListener: OnItemClickListener
        ) {

            when {
                article != null -> {
                    val requestOptions = RequestOptions()
                    requestOptions.placeholder(randomDrawbleColor)
                    requestOptions.error(randomDrawbleColor)
                    requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
                    requestOptions.centerCrop()
                    if (article.urlToImage != null)
                        Glide.with(itemView.context)
                            .load(article.urlToImage)
                            .apply(requestOptions)
                            .listener(object : RequestListener<Drawable?> {
                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any,
                                    target: Target<Drawable?>,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    progressBar!!.visibility = View.GONE
                                    return false
                                }

                                override fun onResourceReady(
                                    resource: Drawable?,
                                    model: Any,
                                    target: Target<Drawable?>,
                                    dataSource: DataSource,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    progressBar!!.visibility = View.GONE
                                    return false
                                }
                            })
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(image!!)
                    title?.text = article.title
                    author?.text = article.author
//                    source?.text = article.source?.id
                    time!!.text = " \u0202 " + Utils.dateToTimeFormat(article.publishedAt)
                    date?.text = Utils.dateFormat(article.publishedAt!!)
//                    name?.text = article.source?.name
                    description?.text = article.description
                    img?.setOnClickListener {
                        onItemClickListener.onItemClick(article, adapterPosition)
                    }

                }
            }

        }

    }

    interface OnItemClickListener {
        fun onItemClick(model: Article, adapterPosition: Int)
    }
}
