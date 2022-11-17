package com.binocularshd.livedataandviewdatakotlin.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.binocularshd.livedataandviewdatakotlin.R
import com.binocularshd.livedataandviewdatakotlin.ViewAndLiveModel.DailyArticleViewModel
import com.binocularshd.livedataandviewdatakotlin.adpater.DailyArticleAdapter
import com.binocularshd.livedataandviewdatakotlin.databinding.MainActivityBinding
import com.binocularshd.livedataandviewdatakotlin.retrofit.DailyArticleResponce
import com.binocularshd.livedataandviewdatakotlin.retrofit.DataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var swipRecyclerView: SwipeRefreshLayout
    lateinit var bindig: MainActivityBinding
    lateinit var dailyArticleAdapter: DailyArticleAdapter
    lateinit var dailyArticleViewModel: DailyArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dailyArticleAdapter = DailyArticleAdapter()
        bindig = DataBindingUtil.setContentView(this, R.layout.main_activity)
        dailyArticleViewModel = ViewModelProvider(this)[DailyArticleViewModel::class.java]
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }


        bindig.swipe.setOnRefreshListener {
            bindig.swipe.isRefreshing = true
            CoroutineScope(Dispatchers.Main).launch {
                dailyArticleViewModel.getArticleDaily()
            }


        }
        bindig.articleRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = dailyArticleAdapter


        }
        dailyArticleViewModel.loading.observe(this) {
            bindig.swipe.isRefreshing = it
        }
        dailyArticleViewModel.getArticleDaily()
        dailyArticleViewModel.getAlldata.observe(this, Observer {


            dailyArticleAdapter.submitList(it as ArrayList<DataItem>)
            // dailyArticleAdapter.setData()

        })


    }
}