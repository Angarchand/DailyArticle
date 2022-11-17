package com.binocularshd.livedataandviewdatakotlin.Activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.binocularshd.livedataandviewdatakotlin.R
import com.binocularshd.livedataandviewdatakotlin.ViewAndLiveModel.DailyArticleViewModel
import com.binocularshd.livedataandviewdatakotlin.adpater.RelatedArticlesAdapter
import com.binocularshd.livedataandviewdatakotlin.databinding.DailyarticleAdpaterBinding
import com.binocularshd.livedataandviewdatakotlin.databinding.ReadArticleViewBinding
import com.binocularshd.livedataandviewdatakotlin.retrofit.Data
import com.binocularshd.livedataandviewdatakotlin.retrofit.DataItem
import com.binocularshd.livedataandviewdatakotlin.retrofit.ReadArticleResponse
import com.binocularshd.livedataandviewdatakotlin.retrofit.RelatedArticlesItem
import com.squareup.picasso.Picasso

class ReadArticleView:AppCompatActivity() {
    lateinit var binding: ReadArticleViewBinding
    lateinit var datamodel: DailyArticleViewModel
    lateinit var RelatedArticleadpater: RelatedArticlesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.read_article_view)
        datamodel = ViewModelProvider(this)[DailyArticleViewModel::class.java]
        val value = getIntent().getStringExtra("slug")

datamodel.readArticle(value.toString())



        datamodel.ReadArticle.observe(this) { result ->

            result?.let {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.content.text = Html.fromHtml(result.article!!.content, 0)
                }
                binding.date.text = result.article!!.date
                binding.title.text = result.article!!.title
                Picasso.get().load(result.article!!.image).into(binding.articleImage)
                binding.progress.visibility = View.INVISIBLE
               // RelatedArticleadpater.submitList(it)

                binding.relatedarticles.setHasFixedSize(true)
                binding.relatedarticles.layoutManager = LinearLayoutManager(this@ReadArticleView)

                binding.relatedarticles.adapter = RelatedArticlesAdapter(result.relatedArticles!! as ArrayList<RelatedArticlesItem>)
                {
                    val position=it
                    val intent=Intent(this@ReadArticleView,ReadArticleView::class.java)
                    intent.putExtra("slug", result.relatedArticles!!.get(position)!!.slug)
                    startActivity(intent)


                }
            }
        }




     /*   datamodel.ReadArticle.observe(this, Observer {
        })
*/
    }
}












