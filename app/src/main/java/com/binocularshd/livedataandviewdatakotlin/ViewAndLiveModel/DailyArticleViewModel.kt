package com.binocularshd.livedataandviewdatakotlin.ViewAndLiveModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binocularshd.livedataandviewdatakotlin.Activities.ReadArticleView
import com.binocularshd.livedataandviewdatakotlin.retrofit.*
import kotlinx.coroutines.*
import retrofit2.Response
import java.util.logging.LoggingMXBean
import kotlin.math.log

class DailyArticleViewModel:ViewModel() {
    val getAlldata = MutableLiveData<ArrayList<DataItem>>()
    val ReadArticle = MutableLiveData<Data>()
    var result: Response<DailyArticleResponce>? = null
    val articleDetail = MutableLiveData<ReadArticleView>()
    val articleError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getArticleDaily() {
        loading.value = true
        // launching a new coroutine
        job = GlobalScope.launch {
            val quotesApi = RetrofitHelper.getInstance()!!.create(RetrofitApi::class.java)
            result = quotesApi.getDailyArticles()
            val dataList = ArrayList<DataItem>()
            for (i in 0..quotesApi.getDailyArticles().body()!!.data!!.size - 1) {
                dataList.add(result!!.body()!!.data!!.get(i)!!)
            }
            getAlldata.postValue(dataList)
            loading.postValue(false)
        }


    }

    /*  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }*/
    fun readArticle(slug: String) {

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val quotesApi = RetrofitHelper.getInstance()!!.create(RetrofitApi::class.java)
            var readresult: Response<ReadArticleResponse>? = null
            readresult = quotesApi.readArticle(slug)

            if (readresult.isSuccessful) {
                val readArticlesitems: ReadArticleResponse = readresult.body()!!
                ReadArticle.postValue(readArticlesitems.data!!)
            }
        }
    }


    private fun onError(message: String) {
        articleError.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}