package com.ssac.ah_jeom.src.main.peek.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.PeekCharacterImageListItemBinding
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.peek.bestStorage.BestStorageActivity
import com.ssac.ah_jeom.src.main.peek.models.PeekMainViewpagerData
import com.ssac.ah_jeom.src.main.peek.newStorage.NewStorageActivity
import com.ssac.ah_jeom.src.main.peek.soaringStorage.SoaringStorageActivity
import com.ssac.ah_jeom.src.main.subscribe.soaringArtist.SoaringArtistActivity

class PeekMainViewpagerAdapter(private val context: Context) :
    RecyclerView.Adapter<PeekMainViewpagerAdapter.PagerViewHolder>() {

    var item = mutableListOf<PeekMainViewpagerData>()

    interface OnItemClickListener {
        fun onItemClick(v: View, data: PeekMainViewpagerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeekMainViewpagerAdapter.PagerViewHolder {
        val binding = PeekCharacterImageListItemBinding.inflate(
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

    inner class PagerViewHolder(val binding: PeekCharacterImageListItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
        }

        fun setData(data: PeekMainViewpagerData) {

            binding.peekCharacterImageView.setImageResource(data.peekMainViewpagerImage)
            binding.peekCharacterFirstText.text = data.firstText
            binding.peekCharacterSecondText.text = data.secondText

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    when (pos) {
                        0 -> {
                            val intent = Intent(itemView.context, BestStorageActivity::class.java)
                            itemView.context.startActivity(intent)
                            (itemView.context as MainActivity).overridePendingTransition(
                                R.anim.activity_fade_in,
                                R.anim.activity_fade_out
                            )
                        }
                        1 -> {
                            val intent = Intent(itemView.context, NewStorageActivity::class.java)
                            itemView.context.startActivity(intent)
                            (itemView.context as MainActivity).overridePendingTransition(
                                R.anim.activity_fade_in,
                                R.anim.activity_fade_out
                            )
                        }
                        2 -> {
                            val intent = Intent(itemView.context, SoaringStorageActivity::class.java)
                            itemView.context.startActivity(intent)
                            (itemView.context as MainActivity).overridePendingTransition(
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