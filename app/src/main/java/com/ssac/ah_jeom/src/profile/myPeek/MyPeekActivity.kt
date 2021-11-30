package com.ssac.ah_jeom.src.profile.myPeek

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityMyPeekBinding
import com.ssac.ah_jeom.src.profile.myPeek.adapter.MyPeekRecyclerAdapter
import com.ssac.ah_jeom.src.profile.myPeek.models.GetMyPeekResponse
import com.ssac.ah_jeom.src.profile.myPeek.models.MyPeekRecyclerData

class MyPeekActivity : BaseActivity<ActivityMyPeekBinding>(ActivityMyPeekBinding::inflate),
    MyPeekActivityView {

    private val data: MutableList<MyPeekRecyclerData> = mutableListOf()
    private var cursor : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityMyPeekBackButton.setOnClickListener {
            onBackPressed()
        }

        MyPeekService(this).tryGetMyPeek(cursor)

    }

    private fun setMyPeekRecyclerView(response: GetMyPeekResponse) {
        val data: MutableList<MyPeekRecyclerData> = data
        var adapter = MyPeekRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activityMyPeekRecyclerView.adapter = adapter
        binding.activityMyPeekRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetMyPeekSuccess(response: GetMyPeekResponse) {
        if (response.isSuccess) {
            if (response.result.save.size != 0) {
                binding.activityMyPeekNoItemText.visibility = View.INVISIBLE
                binding.activityMyPeekRecyclerView.visibility = View.VISIBLE
                response.result.save.forEach {
                    data.add(MyPeekRecyclerData(it.img, it.title, it.nickname, "${it.save}회 다운"))
                }
                setMyPeekRecyclerView(response)
            }
            else {
                binding.activityMyPeekNoItemText.visibility = View.VISIBLE
                binding.activityMyPeekRecyclerView.visibility = View.GONE
            }
        }
    }

    override fun onGetMyPeekFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}