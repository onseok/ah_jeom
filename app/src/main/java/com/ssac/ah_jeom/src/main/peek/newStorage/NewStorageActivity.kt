package com.ssac.ah_jeom.src.main.peek.newStorage

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityNewStorageBinding
import com.ssac.ah_jeom.src.main.peek.bestStorage.models.BestStorageRecyclerData
import com.ssac.ah_jeom.src.main.peek.newStorage.adapter.NewStorageRecyclerAdapter
import com.ssac.ah_jeom.src.main.peek.newStorage.models.GetNewStorageResponse
import com.ssac.ah_jeom.src.main.peek.newStorage.models.NewStorageRecyclerData

class NewStorageActivity : BaseActivity<ActivityNewStorageBinding>(ActivityNewStorageBinding::inflate), NewStorageActivityView {

    private val data: MutableList<NewStorageRecyclerData> = mutableListOf()

    private var cursor: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityNewStorageMainBackButton.setOnClickListener {
            onBackPressed()
        }

        NewStorageService(this).tryGetNewStorage(cursor)
    }

    private fun setBestStorageRecyclerView(response: GetNewStorageResponse) {
        val data: MutableList<NewStorageRecyclerData> = data
        var adapter = NewStorageRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activityNewStorageMainRecyclerView.adapter = adapter
        binding.activityNewStorageMainRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetNewStorageSuccess(response: GetNewStorageResponse) {
        if (response.isSuccess) {
            data.clear()
            response.result.new.forEach {
                data.add(NewStorageRecyclerData(it.profile, it.nickname, "${it.save}회 다운", it.img, it.title, "좋아요 ${it.heart}개", it.likes == 1))
            }
        }
        setBestStorageRecyclerView(response)
    }

    override fun onGetNewStorageFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onResume() {
        super.onResume()
        NewStorageService(this).tryGetNewStorage(cursor)
    }
}