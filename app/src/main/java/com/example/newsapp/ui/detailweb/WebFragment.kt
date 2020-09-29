package com.example.newsapp.ui.detailweb

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import kotlinx.android.synthetic.main.fragment_web.*


class WebFragment : Fragment() {
    private lateinit var webView: WebView
    private lateinit var myUrl: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (progressBar != null) {
            progressBar.visibility = View.VISIBLE
        }
        webView = view.findViewById(R.id.webview)
        webView.webViewClient = object : WebViewClient() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {

                if (request.url.toString() == myUrl) {
                    view.loadUrl(myUrl)
                }
                return true
            }
        }
        if (arguments != null) {
            myUrl = requireArguments().getString("url").toString()
            webView.loadUrl(myUrl)
            progressBar.visibility = View.INVISIBLE
        } else {
            textView.text = "Don't found data, and can't open web site"
        }
        webView.settings.javaScriptEnabled = true
    }
}