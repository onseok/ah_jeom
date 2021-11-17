package com.ssac.ah_jeom.src.main.home.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.FragmentHomeKeywordAddGridRecyclerItemBinding
import com.ssac.ah_jeom.src.main.home.models.UpdateKeywordRecyclerData

class KeywordAddRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<KeywordAddRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<UpdateKeywordRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: UpdateKeywordRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = FragmentHomeKeywordAddGridRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        // isClicked 배열 false로 초기화 (첫 번째 것 빼고)
        for (i in 0 until listData.size) {
            isClickedKeyword.add(false)
        }
        isClickedKeyword[0] = true

        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }


    inner class ProductHolder(val binding: FragmentHomeKeywordAddGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }


        fun setData(data: UpdateKeywordRecyclerData) {

            binding.fragmentHomeKeywordAddGridRecyclerText.text = data.updateKeyword

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {

                if (pos == 0) {
                    binding.fragmentHomeKeywordAddGridRecyclerText.setTextColor(Color.BLUE)
                }

                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    if (!isClickedKeyword[pos]) {
                        binding.fragmentHomeKeywordAddGridRecyclerText.setTextColor(Color.BLUE)
                        isClickedKeyword[pos] = true
                    }
                    else {
                        // do nothing
                        binding.fragmentHomeKeywordAddGridRecyclerText.setTextColor(Color.BLACK)
                        isClickedKeyword[pos] = false
                    }
                }
            }
        }
    }


    companion object {
        var isClickedKeyword = mutableListOf<Boolean>()
    }

}