package com.ssac.ah_jeom.src.main.home

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentHomeBinding
import com.ssac.ah_jeom.src.main.home.adapter.HomeFragmentBestArtistRecyclerAdapter
import com.ssac.ah_jeom.src.main.home.adapter.HomeFragmentViewpagerAdapter
import com.ssac.ah_jeom.src.main.home.adapter.InterestsViewpagerAdapter
import com.ssac.ah_jeom.src.main.home.adapter.KeywordViewpagerAdapter
import com.ssac.ah_jeom.src.main.home.fragments.*
import com.ssac.ah_jeom.src.main.home.models.BestArtist
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private var numBanner = 3
    private var currentPosition = 2
    private var myHandler = MyHandler()
    private val intervalTime = 5000.toLong() // 몇초 간격으로 페이지를 넘길것인지 (5000 = 5.0초)

    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding?.fragmentHomeBannerViewpager?.adapter = HomeFragmentViewpagerAdapter(getBannerList())
        binding?.fragmentHomeBannerViewpager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding?.fragmentHomeBannerViewpager?.setCurrentItem(currentPosition, false) // 현재 위치를 지정

        // 현재 몇 번째 배너인지 보여주는 숫자를 변경함
        val apply = binding!!.fragmentHomeBannerViewpager.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저에서 손 떼었을때 / 뷰페이저 멈춰있을 때
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)

                        // 뷰페이저 움직이는 중
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }

        binding?.fragmentHomeBannerViewpager?.setCurrentItem(++currentPosition, true)

        // magazine 데이터 어댑터 연결
        val bestArtistData: MutableList<BestArtist> = loadData()
        var bestArtistAdapter = HomeFragmentBestArtistRecyclerAdapter()
        bestArtistAdapter.listData = bestArtistData
        binding?.fragmentHomeBestArtistRecycler?.adapter = bestArtistAdapter
        binding?.fragmentHomeBestArtistRecycler?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        // (최애 분야) 탭 레이아웃 연결 전 뷰페이저 어댑터 설정
        val fragmentList = listOf(GraphicFragment(), IllustrationFragment(), ModernArtFragment(), CartoonFragment() ,SculptureFragment(), PhotoFragment(), NaturalFragment(), PersonFragment(), ArchitectureFragment(), ThreeDimensionsFragment(), MoldingFragment())
        val adapter = InterestsViewpagerAdapter(activity as FragmentActivity)
        adapter.fragmentList = fragmentList
        binding?.fragmentHomeInterestsViewpager?.adapter = adapter


        // (최애 분야) 탭 레이아웃 연결
        val tabTitles = listOf("그래픽", "일러스트", "현대미술", "추상", "만화", "조각", "사진", "자연", "인물", "건축", "3D", "조형")
        binding?.fragmentHomeInterestsTabLayout?.let {
            binding?.fragmentHomeInterestsViewpager?.let { it1 ->
                TabLayoutMediator(it, it1) { tab, position ->
                    tab.text = tabTitles[position]
                }.attach()
            }
        }

        // (최애 키워드) 탭 레이아웃 연결 전 뷰페이저 어댑터 설정
        val keywordFragmentList = listOf(KeywordSimpleFragment(), KeywordFancyFragment(), KeywordStrongFragment(), KeywordComfortableFragment() ,KeywordVividFragment(), KeywordCuteFragment(), KeywordBeautifulFragment(), KeywordHolyFragment(), KeywordAbstractFragment(), KeywordRealisticFragment(), KeywordExaggeratedFragment(), KeywordFunFragment(), KeywordScaryFragment(), KeywordBeastlyFragment(), KeywordMysteriousFragment())
        val keywordAdapter = KeywordViewpagerAdapter(activity as FragmentActivity)
        keywordAdapter.fragmentList = keywordFragmentList
        binding?.fragmentHomeKeywordViewpager?.adapter = keywordAdapter


        // (최애 키워드) 탭 레이아웃 연결
        val keywordTabTitles = listOf("심플한", "화려한", "강렬한", "편안한", "원색적인", "귀여운", "아름다운", "성스러운", "추상적인", "실제같은", "과장된", "재밌는", "무서운", "짐승같은", "신비로운")
        binding?.fragmentHomeKeywordTabLayout?.let {
            binding?.fragmentHomeKeywordViewpager?.let { it1 ->
                TabLayoutMediator(it, it1) { tab, position ->
                    tab.text = keywordTabTitles[position]
                }.attach()
            }
        }

        

        return binding!!.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun getBannerList(): ArrayList<Int> {
        return arrayListOf(R.drawable.banner_1_temp, R.drawable.banner_2_temp, R.drawable.banner_3_temp)
    }

    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개 ... n개 만큼 계속 늘어남
        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
    }

    private fun autoScrollStop() {
        myHandler.removeMessages(0) // 핸들러를 중지시킴
    }

    private inner class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if (msg.what == 0) {
                binding?.fragmentHomeBannerViewpager?.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    private fun loadData(): MutableList<BestArtist> {
        val listData: MutableList<BestArtist> = mutableListOf()

        // 임시 mock data, 추후 api 연동으로 바꿀 예정
        listData.add(BestArtist(R.drawable.best_artist_1_temp, "Levan Kenia", R.drawable.best_artist_representative_1_temp))
//        listData.add(BestArtist())
//        listData.add(BestArtist())


        return listData
    }

}