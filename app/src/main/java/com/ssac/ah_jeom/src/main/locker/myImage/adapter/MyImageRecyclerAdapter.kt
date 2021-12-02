package com.ssac.ah_jeom.src.main.locker.myImage.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityMyImageRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artDetail.ArtDetailActivity
import com.ssac.ah_jeom.src.detail.storageDetail.StorageDetailActivity
import com.ssac.ah_jeom.src.main.locker.myImage.MyImageActivity
import com.ssac.ah_jeom.src.main.locker.myImage.models.GetMyImageResponse
import com.ssac.ah_jeom.src.main.locker.myImage.models.MyImageRecyclerData
import com.ssac.ah_jeom.src.main.peek.bestStorage.BestStorageActivity

class MyImageRecyclerAdapter(private val context: Context, response: GetMyImageResponse) :
    RecyclerView.Adapter<MyImageRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<MyImageRecyclerData>()

    val response = response

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

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    for (i in 0 until response.result.myimg.size) {
                        if (pos == i) {
                            val intent = Intent(itemView.context, ArtDetailActivity::class.java)
                            intent.putExtra("artId", response.result.myimg[pos].artId)
                            itemView.context.startActivity(intent)
                            (itemView.context as MyImageActivity).overridePendingTransition(
                                R.anim.activity_fade_in,
                                R.anim.activity_fade_out
                            )
                        }
                    }


                }
            }
        }



    }
}