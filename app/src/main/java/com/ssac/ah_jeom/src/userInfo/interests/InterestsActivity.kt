package com.ssac.ah_jeom.src.userInfo.interests

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.kakao.sdk.user.UserApiClient
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.ApplicationClass
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityInterestsBinding
import com.ssac.ah_jeom.src.login.models.PostLoginRequest
import com.ssac.ah_jeom.src.userInfo.interests.models.PostInterestsRequest
import com.ssac.ah_jeom.src.userInfo.interests.models.PostInterestsResponse
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerAdapter
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerAdapter.Companion.INTERESTS_NEXT_BUTTON
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerAdapter.Companion.fieldIdArray
import com.ssac.ah_jeom.src.userInfo.interests.recycler.InterestsRecyclerData
import com.ssac.ah_jeom.src.userInfo.keyword.KeywordActivity

class InterestsActivity : BaseActivity<ActivityInterestsBinding>(ActivityInterestsBinding::inflate), InterestsActivityView {

    val data: MutableList<InterestsRecyclerData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kakaoAccessToken = intent.getStringExtra("kakaoAccessToken")
        saveKakaoTokens(kakaoAccessToken.toString())

        Log.d("kakaoAccessToken", kakaoAccessToken.toString())

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
                showCustomToast("?????? 1??? ?????? ??????????????????.")
                return@setOnClickListener
            }
            else {
                val userId = ApplicationClass.sSharedPreferences.getInt("userId", 0)

                val params =  PostInterestsRequest(fieldId = fieldIdArray)
                InterestsService(this).tryPostInterests(userId, params)
            }

        }

        binding.activityInterestsAddAllText.setOnClickListener {

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    private fun saveKakaoTokens(accessToken: String) {
        // ?????? ?????? ??????
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                showCustomToast("?????? ?????? ?????? ??????")
            } else if (tokenInfo != null) {
                showCustomToast("?????? ?????? ?????? ??????")

                val accessToken = accessToken

                val editor = ApplicationClass.sSharedPreferences.edit()

                editor.putString("kakaoAccessToken", accessToken)

                editor.commit()

                Log.d("accessToken", accessToken)
            }
        }
    }

    // ????????? api ???????????? ????????? ???. ????????? ?????? mock data
    private fun setData() {
        data.add(InterestsRecyclerData("?????????"))
        data.add(InterestsRecyclerData("????????????"))
        data.add(InterestsRecyclerData("????????????"))
        data.add(InterestsRecyclerData("??????"))
        data.add(InterestsRecyclerData("??????"))
        data.add(InterestsRecyclerData("??????"))
        data.add(InterestsRecyclerData("??????"))
        data.add(InterestsRecyclerData("??????"))
        data.add(InterestsRecyclerData("??????"))
        data.add(InterestsRecyclerData("??????"))
        data.add(InterestsRecyclerData("3D"))
        data.add(InterestsRecyclerData("??????"))
    }

    override fun onPostInterestsSuccess(response: PostInterestsResponse) {
        if (response.isSuccess) {
            startActivity(Intent(this, KeywordActivity::class.java))
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }
    }

    override fun onPostInterestsFailure(message: String) {
        showCustomToast("?????? : $message")
    }

}