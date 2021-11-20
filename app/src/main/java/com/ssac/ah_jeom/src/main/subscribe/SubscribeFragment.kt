package com.ssac.ah_jeom.src.main.subscribe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentSubscribeBinding
import com.ssac.ah_jeom.src.main.subscribe.adapter.SubscribeIllustrationViewpagerAdapter
import com.ssac.ah_jeom.src.main.subscribe.adapter.SubscribeMainViewpagerAdapter
import com.ssac.ah_jeom.src.main.subscribe.models.SubscribeIllustrationData
import com.ssac.ah_jeom.src.main.subscribe.models.SubscribeImageData

class SubscribeFragment : Fragment() {

    private var binding: FragmentSubscribeBinding? = null

    private val illustrationData: MutableList<SubscribeIllustrationData> = mutableListOf()
    private val imageData: MutableList<SubscribeImageData> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscribeBinding.inflate(inflater, container, false)

        setSubscribeIllustrationViewpager()

        setSubscribeMainViewpager()

//        SubscribeIllustrationViewpagerAdapter(requireActivity()).setOnItemClickListener(object :
//            SubscribeIllustrationViewpagerAdapter.OnItemClickListener {
//            override fun onItemClick(v: View, data: SubscribeIllustrationData, pos: Int) {
//                Log.d("viewpager", pos.toString())
//                when (pos) {
//                    0 -> {
//                        startActivity(Intent(requireActivity(), BestArtistActivity::class.java))
//                        (activity as MainActivity).overridePendingTransition(
//                            R.anim.activity_fade_in,
//                            R.anim.activity_fade_out
//                        )
//                    }
//                    1 -> {
//
//                    }
//                    2 -> {
//
//                    }
//                }
//            }
//        })

        return binding!!.root
    }

    // 뷰 페이저에 들어갈 아이템
    private fun getImageList(): ArrayList<Int> {
        return arrayListOf(
            R.drawable.best_artist_illustration_image,
            R.drawable.recently_artist_illustration_image,
            R.drawable.soaring_artist_illustration_image
        )
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setSubscribeIllustrationViewpager() {
        setIllustrationImageData()

        val data: MutableList<SubscribeIllustrationData> = illustrationData
        var adapter = SubscribeIllustrationViewpagerAdapter(requireActivity())
        adapter.item = data
        binding!!.fragmentSubscribeIllustrationViewPager.adapter = adapter
        binding!!.fragmentSubscribeIllustrationViewPager.orientation =
            ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        adapter.notifyDataSetChanged()
    }

    private fun setSubscribeMainViewpager() {
        // 구독 프래그먼트 메인 뷰페이저
        val subscribeImageData: MutableList<SubscribeImageData> = loadData()
        var subscribeImageAdapter = SubscribeMainViewpagerAdapter()
        subscribeImageAdapter.listData = subscribeImageData
        binding?.fragmentSubscribeMainViewpager?.adapter = subscribeImageAdapter

        // 뷰페이저 인디케이터
        val dotsIndicator = binding?.fragmentSubscribeMainIndicator
        val viewPager = binding?.fragmentSubscribeMainViewpager
        viewPager?.adapter = subscribeImageAdapter
        dotsIndicator?.setViewPager2(viewPager!!)
    }

    private fun loadData(): MutableList<SubscribeImageData> {

        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_2_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_3_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_4_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_5_temp))
        imageData.add(SubscribeImageData(R.drawable.subscribe_main_viewpager_image_6_temp))

        return imageData
    }

    private fun setIllustrationImageData() {
        illustrationData.add(SubscribeIllustrationData(R.drawable.best_artist_illustration_image))
        illustrationData.add(SubscribeIllustrationData(R.drawable.recently_artist_illustration_image))
        illustrationData.add(SubscribeIllustrationData(R.drawable.soaring_artist_illustration_image))
    }

}