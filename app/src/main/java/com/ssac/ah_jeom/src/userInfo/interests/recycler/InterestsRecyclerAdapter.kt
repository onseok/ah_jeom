package com.ssac.ah_jeom.src.userInfo.interests.recycler

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityInterestsGridRecyclerItemBinding
import com.ssac.ah_jeom.src.userInfo.interests.InterestsActivity

class InterestsRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<InterestsRecyclerAdapter.ProductHolder>() {

    var listData = mutableListOf<InterestsRecyclerData>()


    interface OnItemClickListener {
        fun onItemClick(v: View, data: InterestsRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ActivityInterestsGridRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        // isClicked 배열 false로 초기화
        for (i in 0 until listData.size) {
            isClicked.add(false)
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


    inner class ProductHolder(val binding: ActivityInterestsGridRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: InterestsRecyclerData) {

            with(binding) {
                activityInterestsGridText.text = data.interests
            }

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    if (!isClicked[pos]) {
                        binding.activityInterestsGridLayout.setBackgroundResource(R.drawable.grid_item_background_selected)
                        isClicked[pos] = true
                        fieldIdArray.add(pos+1)
                        judgeNextButton()
                    } else {
                        binding.activityInterestsGridLayout.setBackgroundResource(R.drawable.grid_item_background_unselected)
                        isClicked[pos] = false
                        fieldIdArray.remove(pos+1)
                        judgeNextButton()
                    }

                }
            }
        }
    }

    private fun judgeNextButton() {
        INTERESTS_NEXT_BUTTON = true in isClicked
        val activity: InterestsActivity = context as InterestsActivity
        if (INTERESTS_NEXT_BUTTON) {
            activity.findViewById<LinearLayout>(R.id.activity_interests_bottom_bar)
                .setBackgroundColor(
                    Color.parseColor("#3F3FFF")
                )
        } else {
            activity.findViewById<LinearLayout>(R.id.activity_interests_bottom_bar)
                .setBackgroundColor(Color.parseColor("#1D1D1D"))
        }
    }

    companion object {
        var INTERESTS_NEXT_BUTTON = false
        var isClicked = mutableListOf<Boolean>()
        var fieldIdArray = mutableListOf<Int>()
    }
}