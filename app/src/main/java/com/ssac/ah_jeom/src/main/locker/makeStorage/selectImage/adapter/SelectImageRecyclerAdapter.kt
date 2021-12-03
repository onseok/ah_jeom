package com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivitySelectImageRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artDetail.ArtDetailActivity
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.SelectImageActivity
import com.ssac.ah_jeom.src.main.locker.makeStorage.selectImage.models.GetSelectImageResponse
import com.ssac.ah_jeom.src.main.locker.myImage.MyImageActivity
import com.ssac.ah_jeom.src.userInfo.interests.InterestsActivity
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerAdapter

class SelectImageRecyclerAdapter(private val context: Context, response: GetSelectImageResponse) :
    RecyclerView.Adapter<SelectImageRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<SelectImageRecyclerData>()

    val response = response

    var isFirst = true

    interface OnItemClickListener {
        fun onItemClick(v: View, data: SelectImageRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivitySelectImageRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        for (i in 0 until listData.size) {
            isSelected.add(false)
            judgeCompleteButton()
            isFirst = false
        }

        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }


    inner class ProductHolder(val binding: ActivitySelectImageRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: SelectImageRecyclerData) {
            Glide.with(itemView.context).load(data.image)
                .into(binding.activitySelectImageRecyclerImage)

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {

                for (i in 0 until response.result.myimg.size) {
                    if (pos == i) {
                        if (isSelected[pos]) {
                            binding.activitySelectImageBackground.visibility = View.VISIBLE
                            binding.activitySelectCheckIcon.visibility = View.VISIBLE
                        }
                        else {
                            binding.activitySelectImageBackground.visibility = View.GONE
                            binding.activitySelectCheckIcon.visibility = View.GONE
                        }
                    }
                }

                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    if (!isSelected[pos]) {
                        binding.activitySelectImageBackground.visibility = View.VISIBLE
                        binding.activitySelectCheckIcon.visibility = View.VISIBLE
                        selectedImagesList.add(response.result.myimg[pos].artId)
                        isSelected[pos] = true
                        judgeCompleteButton()
                    } else if (isSelected[pos]) {
                        binding.activitySelectImageBackground.visibility = View.GONE
                        binding.activitySelectCheckIcon.visibility = View.GONE
                        selectedImagesList.remove(response.result.myimg[pos].artId)
                        isSelected[pos] = false
                        judgeCompleteButton()
                    }

                }
            }
        }


    }

    private fun judgeCompleteButton() {
        COMPLETE_BUTTON = true in isSelected
        val activity = context as SelectImageActivity
        if (COMPLETE_BUTTON) {
            activity.findViewById<TextView>(R.id.activity_select_image_complete_text)
                .setTextColor(
                    Color.parseColor("#3590D3")
                )
        } else {
            activity.findViewById<TextView>(R.id.activity_select_image_complete_text)
                .setTextColor(Color.parseColor("#838383"))
        }
    }

    companion object {
        var COMPLETE_BUTTON = false
        var selectedImagesList = mutableListOf<Int>()
        var isSelected = mutableListOf<Boolean>()
    }
}