package com.ssac.ah_jeom.src.userInfo.interests

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityInterestsBinding
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerAdapter
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerAdapter.Companion.INTERESTS_NEXT_BUTTON
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerData
import com.ssac.ah_jeom.src.userInfo.keyword.KeywordActivity

class InterestsActivity : BaseActivity<ActivityInterestsBinding>(ActivityInterestsBinding::inflate) {

    val data: MutableList<InterestsRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityInterestsBackButton.setOnClickListener {
            onBackPressed()
        }

        val data: MutableList<InterestsRecyclerData> = data
        var adapter = InterestsRecyclerAdapter(this)
        adapter.listData = data
        binding.activityInterestsRecyclerView.adapter = adapter
        binding.activityInterestsRecyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

        setData()

        binding.activityInterestsBottomBar.setOnClickListener {

            if(!INTERESTS_NEXT_BUTTON) {
                showCustomToast("최소 1개 이상 선택해주세요.")
                return@setOnClickListener
            }
            else {
                startActivity(Intent(this, KeywordActivity::class.java))
                overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
            }

        }

        binding.activityInterestsAddAllText.setOnClickListener {

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    // 추후에 api 연동하면 대체될 것. 지금은 임시 mock data
    private fun setData() {
        data.add(InterestsRecyclerData("그래픽"))
        data.add(InterestsRecyclerData("일러스트"))
        data.add(InterestsRecyclerData("현대미술"))
        data.add(InterestsRecyclerData("추상"))
        data.add(InterestsRecyclerData("만화"))
        data.add(InterestsRecyclerData("조각"))
        data.add(InterestsRecyclerData("사진"))
        data.add(InterestsRecyclerData("자연"))
        data.add(InterestsRecyclerData("인물"))
        data.add(InterestsRecyclerData("건축"))
        data.add(InterestsRecyclerData("3D"))
        data.add(InterestsRecyclerData("조형"))
    }

}