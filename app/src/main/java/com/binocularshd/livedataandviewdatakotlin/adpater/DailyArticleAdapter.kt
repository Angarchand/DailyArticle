package com.binocularshd.livedataandviewdatakotlin.adpater

import android.content.Context
import android.content.Intent
import android.hardware.Camera.PictureCallback
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.binocularshd.livedataandviewdatakotlin.Activities.ReadArticleView
import com.binocularshd.livedataandviewdatakotlin.databinding.DailyarticleAdpaterBinding
import com.binocularshd.livedataandviewdatakotlin.databinding.MainActivityBinding
import com.binocularshd.livedataandviewdatakotlin.retrofit.DailyArticleResponce
import com.binocularshd.livedataandviewdatakotlin.retrofit.DataItem
import com.squareup.picasso.Picasso
import kotlin.math.log

class DailyArticleAdapter() : ListAdapter<DataItem, DailyArticleAdapter.ViewHolder>(DiffUtil()) {
    lateinit var binding: DailyarticleAdpaterBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            DailyarticleAdpaterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val items = getItem(position)

        holder.datainsertion(items)

    }

    /*  override fun getItemCount(): Int {
          Log.d("ayushaaavvv: ", arraylist.size.toString())

          return arraylist.size
      }*/

    class ViewHolder(val binding: DailyarticleAdpaterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun datainsertion(list: DataItem) {
            binding.title.text = list.title
            binding.des.text = list.content
            binding.date.text = list.date
            Picasso.get().load(list.image).into(binding.images)

            binding.next.setOnClickListener {


                val context = binding.title.context

                val intent = Intent(context, ReadArticleView::class.java)
                intent.putExtra("slug", list.slug)
                context.startActivity(intent)


            }


        }

    }


}

class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(
        oldItem: DataItem,
        newItem: DataItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DataItem,
        newItem: DataItem
    ): Boolean {

        return oldItem == newItem

    }


}