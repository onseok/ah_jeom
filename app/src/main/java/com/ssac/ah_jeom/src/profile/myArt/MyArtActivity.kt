package com.ssac.ah_jeom.src.profile.myArt

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityMyArtBinding
import com.ssac.ah_jeom.src.profile.myArt.adapter.MyArtRecyclerAdapter
import com.ssac.ah_jeom.src.profile.myArt.models.GetMyArtResponse
import com.ssac.ah_jeom.src.profile.myArt.models.MyArtRecyclerData

class MyArtActivity : BaseActivity<ActivityMyArtBinding>(ActivityMyArtBinding::inflate),
    MyArtActivityView {

    private val data: MutableList<MyArtRecyclerData> = mutableListOf()
    private var cursor : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityMyArtBackButton.setOnClickListener {
            onBackPressed()
        }

        MyArtService(this).tryGetMyArt(cursor)

    }

    private fun setMyArtRecyclerView(response: GetMyArtResponse) {
        val data: MutableList<MyArtRecyclerData> = data
        var adapter = MyArtRecyclerAdapter(this, response)
        adapter.listData = data
        binding.activityMyArtRecyclerView.adapter = adapter
        binding.activityMyArtRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    override fun onGetMyArtSuccess(response: GetMyArtResponse) {
        if (response.code == 3016) {
            binding.activityMyArtNoItemText.visibility = View.VISIBLE
            binding.activityMyArtRecyclerView.visibility = View.GONE
        }

        else if (response.isSuccess) {
            binding.activityMyArtNoItemText.visibility = View.INVISIBLE
            binding.activityMyArtRecyclerView.visibility = View.VISIBLE

            if (response.result.artwork.size != 0) {
                binding.activityMyArtNoItemText.visibility = View.INVISIBLE
                binding.activityMyArtRecyclerView.visibility = View.VISIBLE
                response.result.artwork.forEach {
                    data.add(MyArtRecyclerData(it.img))
                }
                setMyArtRecyclerView(response)
            }
            else {
                binding.activityMyArtNoItemText.visibility = View.VISIBLE
                binding.activityMyArtRecyclerView.visibility = View.GONE
            }
        }
    }

    override fun onGetMyArtFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}