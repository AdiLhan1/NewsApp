package com.example.newsapp.ui.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_fragment.*


class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var model: Article
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navHostFragment = (requireActivity().supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)!!
        navController = navHostFragment.navController
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        // TODO: Use the ViewModel
        if (arguments != null) {
            model = arguments?.getSerializable("model") as Article
            Picasso.get().load(model.urlToImage).into(detail_img)
            if (model.author == null) {
                det_author.text = "Mr.Nobody"
            } else {
                det_author.text = model.author
            }
            Picasso.get().load(model.urlToImage).placeholder(R.drawable.user).into(det_author_img)
            det_title.text = model.title
            det_date.text = model.publishedAt
            det_desc.text = model.description
            det_url.text = " подробнее..."
            val bundle = Bundle()
            bundle.putString("url", model.url)
            det_url.setOnClickListener {
                navController.navigate(R.id.action_detailFragment_to_webFragment, bundle)
            }
        }
        Log.e("ololo", "onActivityCreated: $model")

    }

}