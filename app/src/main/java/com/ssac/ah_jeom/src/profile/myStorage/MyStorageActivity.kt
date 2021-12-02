package com.ssac.ah_jeom.src.profile.myStorage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityMyStorageBinding
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.locker.makeStorage.MakeStorageActivity
import com.ssac.ah_jeom.src.profile.myStorage.adapter.MyStorageRecyclerAdapter
import com.ssac.ah_jeom.src.profile.myStorage.models.GetMyStorageResponse
import com.ssac.ah_jeom.src.profile.myStorage.models.MyStorageRecyclerData
import com.ssac.ah_jeom.src.profile.myStorage.models.PatchMyStorageResponse

class MyStorageActivity : BaseActivity<ActivityMyStorageBinding>(ActivityMyStorageBinding::inflate),
    MyStorageActivityView {

    private val data: MutableList<MyStorageRecyclerData> = mutableListOf()
    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityMyStorageBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.activityMyStorageMakeButton.setOnClickListener {
            val intent = Intent(this, MakeStorageActivity::class.java)
            startActivity(intent)
            overridePendingTransition(
                R.anim.activity_fade_in,
                R.anim.activity_fade_out
            )
        }

        MyStorageService(this).tryGetMyStorage(cursor)

    }

    private fun setMyStorageRecyclerView(response: GetMyStorageResponse) {
        val data: MutableList<MyStorageRecyclerData> = data
        var adapter = MyStorageRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activityMyStorageRecyclerView.adapter = adapter
        binding.activityMyStorageRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetMyStorageSuccess(response: GetMyStorageResponse) {
        if (response.code == 3015) {
            binding.activityMyStorageNoItemText.visibility = View.VISIBLE
            binding.activityMyStorageRecyclerView.visibility = View.GONE
        } else if (response.isSuccess) {
            binding.activityMyStorageNoItemText.visibility = View.INVISIBLE
            binding.activityMyStorageRecyclerView.visibility = View.VISIBLE

            data.clear()
            if (response.result.storage.size != 0) {
                binding.activityMyStorageNoItemText.visibility = View.INVISIBLE
                binding.activityMyStorageRecyclerView.visibility = View.VISIBLE
                response.result.storage.forEach {
                    data.add(MyStorageRecyclerData(it.img, it.title, it.caption))
                }
                setMyStorageRecyclerView(response)
            } else {
                binding.activityMyStorageNoItemText.visibility = View.VISIBLE
                binding.activityMyStorageRecyclerView.visibility = View.GONE
            }
        }
    }

    override fun onGetMyStorageFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onResume() {
        super.onResume()
        MyStorageService(this).tryGetMyStorage(cursor)
    }

}