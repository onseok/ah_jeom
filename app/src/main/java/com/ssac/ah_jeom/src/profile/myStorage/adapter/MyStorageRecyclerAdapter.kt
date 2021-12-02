package com.ssac.ah_jeom.src.profile.myStorage.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.databinding.*
import com.ssac.ah_jeom.src.detail.storageDetail.StorageDetailActivity
import com.ssac.ah_jeom.src.profile.myStorage.DeleteStorageActivityView
import com.ssac.ah_jeom.src.profile.myStorage.DeleteStorageService
import com.ssac.ah_jeom.src.profile.myStorage.MyStorageActivity
import com.ssac.ah_jeom.src.profile.myStorage.MyStorageService
import com.ssac.ah_jeom.src.profile.myStorage.models.GetMyStorageResponse
import com.ssac.ah_jeom.src.profile.myStorage.models.MyStorageRecyclerData
import com.ssac.ah_jeom.src.profile.myStorage.models.PatchMyStorageResponse
import com.ssac.ah_jeom.src.splash.SplashActivity

class MyStorageRecyclerAdapter(private val context: Context, response: GetMyStorageResponse) :
    RecyclerView.Adapter<MyStorageRecyclerAdapter.PagerViewHolder>(), DeleteStorageActivityView {

    var listData = mutableListOf<MyStorageRecyclerData>()

    val response = response

    interface OnItemClickListener {
        fun onItemClick(v: View, data: MyStorageRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyStorageRecyclerAdapter.PagerViewHolder {
        val binding = ActivityMyStorageRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }

    inner class PagerViewHolder(val binding: ActivityMyStorageRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
            binding.activityMyStorageDeleteButton.setOnClickListener { deleteDialog() }
        }

        private fun deleteDialog() {
            val dialog = Dialog(itemView.context)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 백그라운드 컬러 투명 (이걸 해줘야 background가 설정해준 모양으로 변함)
            dialog.setContentView(R.layout.delete_dialog)
            dialog.setCancelable(true)    // 다이얼로그외에 다른 화면을 눌렀을 때 나가는 것 허용

            var deleteYesButton : Button? = dialog.findViewById(R.id.delete_yes_button) // 버튼을 dialog에 연결
            var deleteNoButton : Button? = dialog.findViewById(R.id.delete_no_button)


            deleteYesButton?.setOnClickListener {
                dialog.dismiss()

                val pos = adapterPosition
                DeleteStorageService(this@MyStorageRecyclerAdapter).tryPatchMyStorage(response.result.storage[pos].storageId)
            }

            deleteNoButton?.setOnClickListener{
                dialog.dismiss()
            }

            dialog.show()
        }

        fun setData(data: MyStorageRecyclerData) {

            Glide.with(itemView.context).load(data.image).into(binding.activityMyStorageRecyclerImage)
            binding.activityMyStorageRecyclerTitleText.text = data.title
            binding.activityMyStorageRecyclerCaptionText.text = data.caption

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)

                    for (i in 0 until response.result.storage.size) {
                        if (pos == i) {
                            val intent = Intent(itemView.context, StorageDetailActivity::class.java)
                            intent.putExtra("storageId", response.result.storage[pos].storageId)
                            itemView.context.startActivity(intent)
                            (itemView.context as MyStorageActivity).overridePendingTransition(
                                R.anim.activity_fade_in,
                                R.anim.activity_fade_out
                            )
                        }
                    }

                }
            }
        }

    }

    override fun onPatchMyStorageSuccess(response: PatchMyStorageResponse) {
        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()

    }

    override fun onPatchMyStorageFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
    }
}