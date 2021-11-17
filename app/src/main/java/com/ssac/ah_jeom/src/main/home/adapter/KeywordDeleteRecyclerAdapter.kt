package com.ssac.ah_jeom.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.FragmentHomeKeywordDeleteGridRecyclerItemBinding
import com.ssac.ah_jeom.src.main.home.models.UpdateKeywordRecyclerData

class KeywordDeleteRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<KeywordDeleteRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<UpdateKeywordRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: UpdateKeywordRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = FragmentHomeKeywordDeleteGridRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

//        // isClicked 배열 false로 초기화
//        for (i in 0 until listData.size) {
//            isClicked.add(false)
//        }

        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }


    inner class ProductHolder(val binding: FragmentHomeKeywordDeleteGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: UpdateKeywordRecyclerData) {

            binding.fragmentHomeKeywordDeleteGridRecyclerText.text = data.updateKeyword

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)


                }
            }
        }

    }
}