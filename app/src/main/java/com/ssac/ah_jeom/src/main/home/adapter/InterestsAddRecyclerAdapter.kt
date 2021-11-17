package com.ssac.ah_jeom.src.main.home.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.databinding.FragmentHomeInterestsAddGirdRecyclerItemBinding
import com.ssac.ah_jeom.src.main.home.models.UpdateInterestsRecyclerData

class InterestsAddRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<InterestsAddRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<UpdateInterestsRecyclerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: UpdateInterestsRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = FragmentHomeInterestsAddGirdRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        // isClicked 배열 false로 초기화 (첫 번째 것 빼고)
        for (i in 0 until listData.size) {
            isClicked.add(false)
        }
        isClicked[0] = true

        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }


    inner class ProductHolder(val binding: FragmentHomeInterestsAddGirdRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }


        fun setData(data: UpdateInterestsRecyclerData) {

            binding.fragmentHomeInterestsAddGridRecyclerText.text = data.updateInterests

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {

                if (pos == 0) {
                    binding.fragmentHomeInterestsAddGridRecyclerText.setTextColor(Color.BLUE)
                }

                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    if (!isClicked[pos]) {
                        binding.fragmentHomeInterestsAddGridRecyclerText.setTextColor(Color.BLUE)
                        isClicked[pos] = true
                    }
                    else {
                        // do nothing
                        binding.fragmentHomeInterestsAddGridRecyclerText.setTextColor(Color.BLACK)
                        isClicked[pos] = false
                    }
                }
            }
        }
    }

    private fun detectIsClicked(binding: FragmentHomeInterestsAddGirdRecyclerItemBinding) {
        for (i in 0 until listData.size) {
            if (isClicked[i]) {
                binding.fragmentHomeInterestsAddGridRecyclerText.setTextColor(Color.BLUE)
            }
            else {
                binding.fragmentHomeInterestsAddGridRecyclerText.setTextColor(Color.BLACK)
            }
        }
    }

    companion object {
        var isClicked = mutableListOf<Boolean>()
    }
}