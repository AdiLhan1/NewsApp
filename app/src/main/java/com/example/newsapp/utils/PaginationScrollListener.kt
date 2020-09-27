package com.example.newsapp.utils

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager.itemCount
        val lastVisible = layoutManager.findLastVisibleItemPosition()
        val isLast: Boolean =
            layoutManager.findLastVisibleItemPosition() == layoutManager.findLastVisibleItemPosition()

        Log.e("page", "onScrolled:last " + layoutManager.findLastVisibleItemPosition())
        if (isLast) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()
    abstract val isLastPage: Boolean
    abstract val isLoading: Boolean

}
