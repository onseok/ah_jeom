package com.ssac.ah_jeom.src.userInfo.keyword

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityKeywordBinding
import com.ssac.ah_jeom.src.userInfo.confirm.ConfirmActivity
import com.ssac.ah_jeom.src.userInfo.keyword.models.PostKeywordRequest
import com.ssac.ah_jeom.src.userInfo.keyword.models.PostKeywordResponse
import com.ssac.ah_jeom.src.userInfo.keyword.recycler.KeywordRecyclerAdapter
import com.ssac.ah_jeom.src.userInfo.keyword.recycler.KeywordRecyclerAdapter.Companion.KEYWORD_NEXT_BUTTON
import com.ssac.ah_jeom.src.userInfo.keyword.recycler.KeywordRecyclerAdapter.Companion.fieldIdArray
import com.ssac.ah_jeom.src.userInfo.keyword.recycler.KeywordRecyclerData

class KeywordActivity : BaseActivity<ActivityKeywordBinding>(ActivityKeywordBinding::inflate), KeywordActivityView {

    private val data: MutableList<KeywordRecyclerData> = mutableListOf()

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


        setData()

        binding.activityKeywordBottomBar.setOnClickListener {

            if(!KEYWORD_NEXT_BUTTON) {
                showCustomToast("최소 1개 이상 선택해주세요.")
                return@setOnClickListener
            }
            else {

                val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

                val params =  PostKeywordRequest(fieldId = fieldIdArray)
                KeywordService(this).tryPostKeyword(userId, params)

            }

        }

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

    override fun onPostKeywordSuccess(response: PostKeywordResponse) {
        if(response.isSuccess) {
            startActivity(Intent(this, ConfirmActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }
    }

    override fun onPostKeywordFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}