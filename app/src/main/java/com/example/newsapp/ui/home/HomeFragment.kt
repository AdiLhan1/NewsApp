package com.example.newsapp.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.data.intrface.SendDataListener
import com.example.newsapp.models.Article
import com.example.newsapp.models.News
import com.example.newsapp.utils.InternetHelper
import com.example.newsapp.utils.UiHelper
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment(), NewsAdapter.OnItemClickListener {
    private lateinit var comm: SendDataListener
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: NewsAdapter
    private var country: String = "ru"
    private var apiKey: String = "e9f0e3118ad44e7c985b758271b4ebdb"

    //    private val networkMonitor = NetworkMonitorUtil(requireActivity())
    var isLoading = false
    var page: Int = 1
    private var limit: Int = 10
    private var pos: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        comm = activity as SendDataListener
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        initRecycler()
        swiperefresh.setOnRefreshListener {
            fetchNewData()
        }
        fetchNews()
//        fetchNewData()
//        getDataFromDatabase()
    }

    private fun updateDatabaseNews(model: News?) {
        model?.let { viewModel.insertNewsData(it) }
    }

    private fun fetchNews() {
        swiperefresh.isRefreshing = true
        if (InternetHelper().checkInternetConnection(requireActivity())) {
            val data = viewModel.getNewsListData(country, apiKey, page, limit)
            data.observe(requireActivity(), Observer<News> {
                val model: News? = data.value
                when {
                    model != null -> {
                        progress_bar.visibility = View.GONE
                        swiperefresh.isRefreshing = false
                        Log.e("internet", "fetchNews: 1")
                        viewModel.deleteAllNews()
                        updateDatabaseNews(model)
                        updateAdapterData(model)
                    }
                }
            })
        } else {
            Log.e("internet", "fetchNews: 2")
            val model = viewModel.getNewsFromDB()
            if (model != null && !model.articles.isNullOrEmpty()) {
                progress_bar.visibility = View.GONE
                swiperefresh.isRefreshing = false
                isLoading = false
                Log.e(
                    "database",
                    "getDataFromDatabase: getNewsFromDb model: ${model.articles.toString()}"
                )
                updateAdapterData(model)
                fetchNewData()
            }
            progress_bar.visibility = View.GONE
            swiperefresh.isRefreshing = false
            isLoading = false
            UiHelper().showToast(requireContext(), "No internet connection")
        }
    }

    private fun initRecycler() {
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.isNestedScrollingEnabled = false
        val layoutManager = LinearLayoutManager(activity)
        recyclerview.layoutManager = layoutManager
        adapter = NewsAdapter(this@HomeFragment)
        recyclerview.adapter = adapter
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val total = adapter.itemCount
                    if (!isLoading) {

                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            val countDownTimer: CountDownTimer =
                                (object : CountDownTimer(2000, 1000) {
                                    override fun onFinish() {
                                        if (swiperefresh != null) {
                                            swiperefresh.isRefreshing = false
                                        }
                                        page += 1
                                        fetchNewData()
                                    }

                                    override fun onTick(p0: Long) {
                                        if (progress_bar != null) {
                                            progress_bar.visibility = View.VISIBLE
                                        }
                                    }
                                })
                            countDownTimer.start()
                        }
                    }

                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    private fun fetchNewData() {
        if (swiperefresh != null) {
            swiperefresh.isRefreshing = true
        }
        isLoading = true
        val data =
            viewModel.getNewsListData(country, apiKey, page, limit)
        data.observe(viewLifecycleOwner, Observer<News> {
            val model: News? = data.value
            when {
                data.value != null -> {
                    if (model != null) {
                        progress_bar.visibility = View.GONE
                        swiperefresh.isRefreshing = false
                        isLoading = false
                        viewModel.deleteAllNews()
                        updateDatabaseNews(model)
                        updateAdapterData(model)
                    }
                }
                else -> {
                    progress_bar.visibility = View.GONE
                    swiperefresh.isRefreshing = false
                    isLoading = false
                }
            }
        })
    }

    private fun updateAdapterData(model: News) {
        val data = model.articles
        adapter.updateData(data)
    }

    override fun onItemClick(model: Article, adapterPosition: Int) {
        pos = adapterPosition
        comm.passDataCom(
            model
        )
    }
}