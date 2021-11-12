package com.ssac.ah_jeom.src.main.home

import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentHomeBinding
import com.ssac.ah_jeom.src.main.home.adapter.*
import com.ssac.ah_jeom.src.main.home.fragments.*
import com.ssac.ah_jeom.src.main.home.models.BestArtist
import com.ssac.ah_jeom.src.main.home.models.GetHomeResponse
import com.ssac.ah_jeom.src.main.home.models.NewArtist
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), HomeFragmentView {

    private var binding: FragmentHomeBinding? = null

    private var numBanner = 3
    private var currentPosition = 2
    private var myHandler = MyHandler()
    private val intervalTime = 5000.toLong() // 몇초 간격으로 페이지를 넘길것인지 (5000 = 5.0초)

    private val fragmentList = mutableListOf<Fragment>()
    private val tabTitles = mutableListOf<String>()

    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 홈 화면 API 연동
        HomeService(this).tryGetHome()


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
        listData.add(BestArtist(R.drawable.best_artist_2_temp, "이철원", R.drawable.best_artist_representative_2_temp))
        listData.add(BestArtist(R.drawable.best_artist_3_temp, "HAna336", R.drawable.best_artist_representative_3_temp))

        return listData
    }

    private fun newArtistLoadData(): MutableList<NewArtist> {
        val newArtistListData: MutableList<NewArtist> = mutableListOf()
        
        // 임시 mock data, 추후 api 연동으로 대체할 예정
        newArtistListData.add(NewArtist("김새롬", R.drawable.new_artist_profile_image_1_temp, R.drawable.new_artist_art_image_1_first_temp, R.drawable.new_artist_art_image_1_second_temp, "나는 자연을 좋아합니다, 무척 새롭죠.\n그래서 찍는건 즐거워요."))
        newArtistListData.add(NewArtist("ginhan456", R.drawable.new_artist_profile_image_2_temp, R.drawable.new_artist_art_image_2_first_temp, R.drawable.new_artist_art_image_2_second_temp, "낡은 것을 보면 여러분은\n무엇을 떠올리시나요?"))
        newArtistListData.add(NewArtist("신재원", R.drawable.new_artist_profile_image_3_temp, R.drawable.new_artist_art_image_3_first_temp, R.drawable.new_artist_art_image_3_second_temp, "예술은 자연이 완결짓지\n못한 것을 완성합니다."))

        return newArtistListData

    }

    override fun onGetHomeSuccess(response: GetHomeResponse) {
        // 배너 설정
        setBannerViewPager()

        // 최애 분야 탭 레이아웃 설정 (서버, 동적으로)
        setInterests(response)

        // 최애 키워드 탭 레이아웃 설정 (서버, 동적으로)
        setKeyword(response)

        // 최고의 아티스트 뷰페이저 설정
        setBestArtistViewPager()

        // 새로운 아티스트 뷰페이저 설정
        setNewArtistViewPager()
    }

    override fun onGetHomeFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
    }

    private fun setBannerViewPager() {
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
    }

    private fun setInterests(response: GetHomeResponse) {
        if (response.field._1 != null) {
            // 우선 첫번째 이미지만 서버에서 받아오자! (뷰페이저 구현은 나중에 생각)
            fragmentList.add(GraphicFragment(response.field._1[0].img))
            tabTitles.add("그래픽")
        }
        if (response.field._2 != null) {
            fragmentList.add(IllustrationFragment(response.field._2[0].img))
            tabTitles.add("일러스트")
        }
        if (response.field._3 != null) {
            fragmentList.add(ModernArtFragment(response.field._3[0].img))
            tabTitles.add("현대미술")
        }
        if (response.field._4 != null) {
            fragmentList.add(AbstractFragment(response.field._4[0].img))
            tabTitles.add("추상")
        }
        if (response.field._5 != null) {
            fragmentList.add(CartoonFragment(response.field._5[0].img))
            tabTitles.add("만화")
        }
        if (response.field._6 != null) {
            fragmentList.add(SculptureFragment(response.field._6[0].img))
            tabTitles.add("조각")
        }
        if (response.field._7 != null) {
            fragmentList.add(PhotoFragment(response.field._7[0].img))
            tabTitles.add("사진")
        }
        if (response.field._8 != null) {
            fragmentList.add(NaturalFragment(response.field._8[0].img))
            tabTitles.add("자연")
        }
        if (response.field._9 != null) {
            fragmentList.add(PersonFragment(response.field._9[0].img))
            tabTitles.add("인물")
        }
        if (response.field._10 != null) {
            fragmentList.add(ArchitectureFragment(response.field._10[0].img))
            tabTitles.add("건축")
        }
        if (response.field._11 != null) {
            fragmentList.add(ThreeDimensionsFragment(response.field._11[0].img))
            tabTitles.add("3D")
        }
        if (response.field._12 != null) {
            fragmentList.add(MoldingFragment(response.field._12[0].img))
            tabTitles.add("조형")
        }

        // (최애 분야) 탭 레이아웃 연결 전 뷰페이저 어댑터 설정
        val adapter = InterestsViewpagerAdapter(activity as FragmentActivity)
        adapter.fragmentList = fragmentList
        binding?.fragmentHomeInterestsViewpager?.adapter = adapter

        // (최애 분야) 탭 레이아웃 연결
        binding?.fragmentHomeInterestsTabLayout?.let {
            binding?.fragmentHomeInterestsViewpager?.let { it1 ->
                TabLayoutMediator(it, it1) { tab, position ->
                    tab.text = tabTitles[position]
                }.attach()
            }
        }

    }

    private fun setKeyword(response: GetHomeResponse) {
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

    }

    private fun setBestArtistViewPager() {
        // 최고의 아티스트 데이터 어댑터 연결
        val bestArtistData: MutableList<BestArtist> = loadData()
        var bestArtistAdapter = HomeFragmentBestArtistRecyclerAdapter()
        bestArtistAdapter.listData = bestArtistData
        binding?.fragmentHomeBestArtistRecycler?.adapter = bestArtistAdapter
        binding?.fragmentHomeBestArtistRecycler?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setNewArtistViewPager() {
        // 새로운 아티스트 뷰페이저
        val newArtistData: MutableList<NewArtist> = newArtistLoadData()
        var newArtistAdapter = NewArtistViewpagerAdapter()
        newArtistAdapter.listData = newArtistData
        binding?.fragmentHomeNewArtistViewpager?.adapter = newArtistAdapter
    }



}