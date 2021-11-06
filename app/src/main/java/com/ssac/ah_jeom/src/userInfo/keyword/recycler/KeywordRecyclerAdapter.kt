package com.ssac.ah_jeom.src.userInfo.keyword.recycler

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityKeywordGridRecyclerItemBinding
import com.ssac.ah_jeom.src.userInfo.keyword.KeywordActivity

class KeywordRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<KeywordRecyclerAdapter.ProductHolder>(){

    var listData = mutableListOf<KeywordRecyclerData>()

    interface OnItemClickListener{
        fun onItemClick(v: View, data: KeywordRecyclerData, pos : Int)
    }

    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivityKeywordGridRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        // isClicked 배열 false로 초기화
        for (i in 0 until listData.size) {
            keywordIsClicked.add(false)
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


    inner class ProductHolder(val binding: ActivityKeywordGridRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }


        fun setData(data: KeywordRecyclerData) {

            with(binding) {
                activityKeywordGridText.text = data.keyword
            }


            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    if (!keywordIsClicked[pos]) {
                        binding.activityKeywordGridLayout.setBackgroundResource(R.drawable.grid_item_background_selected)
                        keywordIsClicked[pos] = true
                        judgeNextButton()
                    }
                    else {
                        binding.activityKeywordGridLayout.setBackgroundResource(R.drawable.grid_item_background_unselected)
                        keywordIsClicked[pos] = false
                        judgeNextButton()
                    }

                    Log.d("isClicked", KEYWORD_NEXT_BUTTON.toString())

                }
            }
        }
    }

    private fun judgeNextButton() {
        KEYWORD_NEXT_BUTTON= true in keywordIsClicked
        val activity: KeywordActivity = context as KeywordActivity
        if (KEYWORD_NEXT_BUTTON) {
            activity.findViewById<LinearLayout>(R.id.activity_keyword_bottom_bar)
                .setBackgroundColor(
                    Color.parseColor("#3F3FFF")
                )
        } else {
            activity.findViewById<LinearLayout>(R.id.activity_keyword_bottom_bar)
                .setBackgroundColor(Color.parseColor("#1D1D1D"))
        }
    }

    companion object {
        var KEYWORD_NEXT_BUTTON = false
        var keywordIsClicked = mutableListOf<Boolean>()
    }
}