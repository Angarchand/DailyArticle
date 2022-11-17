package com.binocularshd.livedataandviewdatakotlin.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.binocularshd.livedataandviewdatakotlin.databinding.DailyarticleAdpaterBinding
import com.binocularshd.livedataandviewdatakotlin.retrofit.Article
import com.binocularshd.livedataandviewdatakotlin.retrofit.Data
import com.binocularshd.livedataandviewdatakotlin.retrofit.ReadArticleResponse
import com.binocularshd.livedataandviewdatakotlin.retrofit.RelatedArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.combine

class RelatedArticlesAdapter constructor(val list:ArrayList<RelatedArticlesItem>,private val onItemClick: (position: Int) -> Unit)
    :RecyclerView.Adapter<RelatedArticlesAdapter.ViewHolder>() {
   lateinit var binding: DailyarticleAdpaterBinding




    class ViewHolder(var binding:DailyarticleAdpaterBinding):RecyclerView.ViewHolder(binding.root){
        fun ReadData(items:RelatedArticlesItem,position: Int)
        {



            Picasso.get().load(items.image).into(binding.images)
            binding.title.text= items.title
            binding.des.text= items.content



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       binding= DailyarticleAdpaterBinding.inflate(LayoutInflater.from(parent.context),parent,false)


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       val items=list[position]
        holder.ReadData(items,position)
        binding.next.setOnClickListener{
            onItemClick(position)
        }

    }

    class DiffUtil(): androidx.recyclerview.widget.DiffUtil.ItemCallback<Data>()
    {
        override fun areItemsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
        return  oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
           return  oldItem==newItem
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
