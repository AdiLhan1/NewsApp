package com.example.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.data.`interface`.SendDataListener
import com.example.newsapp.models.Article
import com.example.newsapp.models.News
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment(), NewsAdapter.OnItemClickListener {
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: NewsAdapter
    lateinit var comm: SendDataListener


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
        initTab()
        initRecycler()
        swiperefresh.setOnRefreshListener {
            swiperefresh.isRefreshing = false
        }
    }

    private fun initTab() {
        tabLayout.addTab(tabLayout.newTab().setText("Русский"))
        tabLayout.addTab(tabLayout.newTab().setText("English"))
        tabLayout.addTab(tabLayout.newTab().setText("日本語"))
        tabLayout.addTab(tabLayout.newTab().setText("한국어"))
        tabLayout.addTab(tabLayout.newTab().setText("Türk"))

        val onTabSelectedListener: TabLayout.OnTabSelectedListener = object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tabLayout.selectedTabPosition) {
                    0 -> fetchNewsList("ru")
                    1 -> fetchNewsList("us")
                    2 -> fetchNewsList("jp")
                    3 -> fetchNewsList("kr")
                    4 -> fetchNewsList("tr")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        }
        onTabSelectedListener.onTabSelected(tabLayout.getTabAt(tabLayout.selectedTabPosition))
        tabLayout.addOnTabSelectedListener(onTabSelectedListener)

    }

    private fun initRecycler() {
        adapter = NewsAdapter(this@HomeFragment)
        recyclerview.adapter = adapter
        recyclerview.addItemDecoration(object : DividerItemDecoration(context, VERTICAL) {})
    }

    private fun clickItem(item: Article) {
        Toast.makeText(context, item.toString(), Toast.LENGTH_LONG).show()
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_homeFragment_to_detailFragment)
    }

    private fun fetchNewsList(country: String) {
        val data = viewModel.getNewsListData(country, "3fc691db3d6342bca61f7d85afa061e0")
        data.observe(viewLifecycleOwner, Observer<News> {
            val model: News? = data.value
            when {
                model != null -> {
                    updateAdapterData(model)
                }
            }
        })
    }

    private fun updateAdapterData(model: News) {
        val data = model.articles
        adapter.updateData(data)
    }

    override fun onItemClick(model: Article) {
        comm.passDataCom(
            model
        )
    }

}