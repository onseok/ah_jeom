package com.ssac.ah_jeom.src.main.locker.myImage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.databinding.ActivityMyImageRecyclerItemBinding
import com.ssac.ah_jeom.src.main.locker.myImage.models.MyImageRecyclerData

class MyImageRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<MyImageRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<MyImageRecyclerData>()


    interface OnItemClickListener {
        fun onItemClick(v: View, data: MyImageRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivityMyImageRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }


    inner class ProductHolder(val binding: ActivityMyImageRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: MyImageRecyclerData) {
            Glide.with(itemView.context).load(data.myImage).into(binding.activityMyImageRecyclerImage)
        }

    }
}