package com.example.newsapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapp.R
import com.example.newsapp.data.`interface`.SendDataListener
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SendDataListener {
    public lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.navigate(R.id.homeFragment)
        refreshApp()
    }

    private fun refreshApp() {

    }

    override fun passDataCom(model: Article) {
        val bundle = Bundle()
        bundle.putSerializable("model", model)
        navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}