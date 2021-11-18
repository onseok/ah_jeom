package com.ssac.ah_jeom.src.main.peek

import android.app.ActivityOptions
import android.content.Intent
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentPeekBinding
import com.ssac.ah_jeom.src.detail.PeekDetailActivity
import com.ssac.ah_jeom.src.main.peek.adapter.PeekMainViewpagerAdapter

class PeekFragment : Fragment() {

    private var binding: FragmentPeekBinding? = null

    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPeekBinding.inflate(inflater, container, false)

        binding!!.fragmentPeekMainViewPager.adapter = PeekMainViewpagerAdapter(getImageList()) // 어댑터 생성
        binding!!.fragmentPeekMainViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로


        val Pair1 = Pair<View,String>(
            binding!!.fragmentPeekImageView, "peek_image")

        val Pair2 = Pair<View,String>(
            binding!!.fragmentPeekImageView, "peek_image")

        binding!!.fragmentPeekImageView.setOnClickListener {
            val intent = Intent(requireActivity(), PeekDetailActivity::class.java)
            var options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(requireActivity())
            startActivity(intent, options.toBundle())
        }

        Glide.with(this).load(R.drawable.fragment_peek_profile_image_temp).circleCrop().into(binding!!.fragmentPeekMainProfileImage)

        return binding!!.root
    }

    // 뷰 페이저에 들어갈 아이템
    private fun getImageList(): ArrayList<Int> {
        return arrayListOf(R.drawable.fragment_peek_best_storage, R.drawable.fragment_peek_new_storage, R.drawable.fragment_peek_soaring_storage)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}