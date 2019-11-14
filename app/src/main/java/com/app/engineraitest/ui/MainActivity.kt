package com.app.engineraitest.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.engineraitest.R
import com.app.engineraitest.data.ClsPost
import com.app.engineraitest.interfaces.SetOnCheckChangeListener
import com.app.engineraitest.network.APIClient
import com.app.engineraitest.network.APIInterface
import com.app.engineraitest.ui.adapters.PostAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    private var postAdapter: PostAdapter? = null
    private var selectedItems = 0
    private var pageNum = 1
    private var isLoading: Boolean = false
    private var hits: ArrayList<ClsPost.Hit> = ArrayList()
    private var isAllPageLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        getPosts()
        listPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == hits.size - 1) {
                        //bottom of list!
                        if(!isAllPageLoaded) {
                            getPosts()
                            isLoading = true
                        }
                    }
                }
            }
        })

        swipeLayout.setOnRefreshListener {
            pageNum = 1
            hits.clear()
            isAllPageLoaded = false
            selectedItems = 0
            txtHeader.text =
                if (selectedItems > 0) "$selectedItems Selected" else getString(R.string.app_name)
            getPosts()
        }
    }

    private fun getPosts() {
        isLoading = true
        val callPosts: Call<ClsPost> =
            APIClient().getClient()!!.create(APIInterface::class.java).getPosts("story", pageNum.toString())
        callPosts.enqueue(object : Callback<ClsPost> {
            override fun onResponse(call: Call<ClsPost>?, response: Response<ClsPost>?) {
                swipeLayout.isRefreshing = false
                if (response?.body() != null) {
                    Log.e("Response", response?.body().toString())
                    if (pageNum == 1)
                        hits.clear()
                    hits.addAll(response?.body().hits)
                    pageNum++
                    setAdapter()
                    isAllPageLoaded = pageNum > response?.body().nbPages
                }
            }

            override fun onFailure(call: Call<ClsPost>?, t: Throwable?) {
            }

        })
    }

    private fun setAdapter() {
        isLoading = false
        postAdapter = PostAdapter(this, hits, object : SetOnCheckChangeListener {
            override fun onCheckChangeListener(selected: Boolean) {
                selectedItems = if (selected) selectedItems + 1 else selectedItems - 1
                txtHeader.text =
                    if (selectedItems > 0) "$selectedItems Selected" else getString(R.string.app_name)
            }
        })
        listPost.adapter = postAdapter

    }
}
