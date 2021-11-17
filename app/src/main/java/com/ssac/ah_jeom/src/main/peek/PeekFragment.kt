package com.ssac.ah_jeom.src.main.peek

import android.app.ActivityOptions
import android.content.Intent
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentHomeBinding
import com.ssac.ah_jeom.databinding.FragmentPeekBinding
import com.ssac.ah_jeom.src.detail.PeekDetailActivity
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.home.adapter.*
import com.ssac.ah_jeom.src.main.home.fragments.*
import com.ssac.ah_jeom.src.main.home.models.BestArtist
import com.ssac.ah_jeom.src.main.home.models.NewArtist
import com.ssac.ah_jeom.src.main.peek.adapter.OthersViewpagerAdapter
import com.ssac.ah_jeom.src.main.peek.fragments.OthersBestFragment
import com.ssac.ah_jeom.src.main.peek.fragments.OthersRecentlyFragment
import com.ssac.ah_jeom.src.main.peek.fragments.OthersSoaringFragment

class PeekFragment : Fragment() {

    private var binding: FragmentPeekBinding? = null

    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPeekBinding.inflate(inflater, container, false)

        // (다른 사람은 어때?) 탭 레이아웃 연결 전 뷰페이저 어댑터 설정
        val fragmentList = listOf(
            OthersBestFragment(),
            OthersRecentlyFragment(),
            OthersSoaringFragment()
        )
        val adapter = OthersViewpagerAdapter(activity as FragmentActivity)
        adapter.fragmentList = fragmentList
        binding?.fragmentPeekMainViewpager?.adapter = adapter


        // (최애 분야) 탭 레이아웃 연결
        val tabTitles =
            listOf("베스트", "최근", "급상승")
        binding?.fragmentPeekMainTabLayout?.let {
            binding?.fragmentPeekMainViewpager?.let { it1 ->
                TabLayoutMediator(it, it1) { tab, position ->
                    tab.text = tabTitles[position]
                }.attach()
            }
        }




        val Pair1 = Pair<View,String>(
            binding!!.fragmentPeekImageView, "peek_image")

        val Pair2 = Pair<View,String>(
            binding!!.fragmentPeekImageView, "peek_image")

        binding!!.fragmentPeekImageView.setOnClickListener {
            val intent = Intent(requireActivity(), PeekDetailActivity::class.java)
            var options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(requireActivity())
            startActivity(intent, options.toBundle())
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}