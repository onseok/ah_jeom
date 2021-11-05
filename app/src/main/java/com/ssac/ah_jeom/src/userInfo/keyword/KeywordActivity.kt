package com.ssac.ah_jeom.src.userInfo.keyword

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityKeywordBinding
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerAdapter
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerData
import com.ssac.ah_jeom.src.userInfo.keyword.recycler.KeywordRecyclerAdapter
import com.ssac.ah_jeom.src.userInfo.keyword.recycler.KeywordRecyclerData

class KeywordActivity : BaseActivity<ActivityKeywordBinding>(ActivityKeywordBinding::inflate) {

    val data: MutableList<KeywordRecyclerData> = mutableListOf()
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityKeywordBackButton.setOnClickListener {
            onBackPressed()
        }

        val data: MutableList<KeywordRecyclerData> = data
        var adapter = KeywordRecyclerAdapter(this)
        adapter.listData = data
        binding.activityKeywordRecyclerView.adapter = adapter
        binding.activityKeywordRecyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()


        adapter.setOnItemClickListener(object : KeywordRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: KeywordRecyclerData, pos: Int) {

                if(KeywordRecyclerAdapter.NEXT_BUTTON) {
                    binding.activityKeywordBottomBar.setBackgroundColor(resources.getColor(R.color.main_blue))
                }
                else {
                    binding.activityKeywordBottomBar.setBackgroundColor(resources.getColor(R.color.main_black))
                }
            }
        })

        setData()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    // 추후에 api 연동하면 대체될 것. 지금은 임시 mock data
    private fun setData() {
        data.add(KeywordRecyclerData("심플한"))
        data.add(KeywordRecyclerData("화려한"))
        data.add(KeywordRecyclerData("강렬한"))
        data.add(KeywordRecyclerData("편안한"))
        data.add(KeywordRecyclerData("원색적인"))
        data.add(KeywordRecyclerData("귀여운"))
        data.add(KeywordRecyclerData("아름다운"))
        data.add(KeywordRecyclerData("성스러운"))
        data.add(KeywordRecyclerData("추상적인"))
        data.add(KeywordRecyclerData("실제같은"))
        data.add(KeywordRecyclerData("과장된"))
        data.add(KeywordRecyclerData("재밌는"))
        data.add(KeywordRecyclerData("무서운"))
        data.add(KeywordRecyclerData("짐승같은"))
        data.add(KeywordRecyclerData("신비로운"))
    }
}