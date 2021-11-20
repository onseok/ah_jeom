package com.ssac.ah_jeom.src.main.subscribe.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.SubscribeCharacterImageListItemBinding
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.subscribe.bestArtist.BestArtistActivity
import com.ssac.ah_jeom.src.main.subscribe.models.SubscribeIllustrationData

class SubscribeIllustrationViewpagerAdapter(private val context: Context) :
    RecyclerView.Adapter<SubscribeIllustrationViewpagerAdapter.PagerViewHolder>() {

    var item = mutableListOf<SubscribeIllustrationData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: SubscribeIllustrationData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubscribeIllustrationViewpagerAdapter.PagerViewHolder {
        val binding = SubscribeCharacterImageListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val data = item[position]
        holder.setData(data)
    }

    inner class PagerViewHolder(val binding: SubscribeCharacterImageListItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: SubscribeIllustrationData) {

            binding.subscribeCharacterImageView.setImageResource(data.subscribeIllustrationData)

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    when (pos) {
                        0 -> {
                            val intent = Intent(itemView.context, BestArtistActivity::class.java)
                            itemView.context.startActivity(intent)
                            (itemView.context as MainActivity).overridePendingTransition(
                                R.anim.activity_fade_in,
                                R.anim.activity_fade_out
                            )
                        }
                        1 -> {

                        }
                        2 -> {

                        }
                    }

                }
            }
        }

    }
}