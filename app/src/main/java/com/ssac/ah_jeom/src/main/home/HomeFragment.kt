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

    private val keywordFragmentList = mutableListOf<Fragment>()
    private val keywordTabTitles = mutableListOf<String>()

    private val listData: MutableList<BestArtist> = mutableListOf()
    private val newArtistListData: MutableList<NewArtist> = mutableListOf()

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

    private fun loadData(response: GetHomeResponse): MutableList<BestArtist> {

        response.best.forEach {
            listData.add(BestArtist(it.nickname, it.profile))
        }

        return listData
    }

    private fun newArtistLoadData(response: GetHomeResponse): MutableList<NewArtist> {

        response.new.forEach {
            newArtistListData.add(NewArtist(it.nickname, it.profile))
        }

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
        setBestArtistViewPager(response)

        // 새로운 아티스트 뷰페이저 설정
        setNewArtistViewPager(response)
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
        if (response.kw._13 != null) {
            // 우선 첫번째 이미지만 서버에서 받아오자! (뷰페이저 구현은 나중에 생각)
            keywordFragmentList.add(KeywordSimpleFragment(response.kw._13[0].img))
            keywordTabTitles.add("심플한")
        }
        if (response.kw._14 != null) {
            keywordFragmentList.add(KeywordFancyFragment(response.kw._14[0].img))
            keywordTabTitles.add("화려한")
        }
        if (response.kw._15 != null) {
            keywordFragmentList.add(KeywordStrongFragment(response.kw._15[0].img))
            keywordTabTitles.add("강렬한")
        }
        if (response.kw._16 != null) {
            keywordFragmentList.add(KeywordComfortableFragment(response.kw._16[0].img))
            keywordTabTitles.add("편안한")
        }
        if (response.kw._17 != null) {
            keywordFragmentList.add(KeywordVividFragment(response.kw._17[0].img))
            keywordTabTitles.add("원색적인")
        }
        if (response.kw._18 != null) {
            keywordFragmentList.add(KeywordCuteFragment(response.kw._18[0].img))
            keywordTabTitles.add("귀여운")
        }
        if (response.kw._19 != null) {
            keywordFragmentList.add(KeywordBeautifulFragment(response.kw._19[0].img))
            keywordTabTitles.add("아름다운")
        }
        if (response.kw._20 != null) {
            keywordFragmentList.add(KeywordHolyFragment(response.kw._20[0].img))
            keywordTabTitles.add("성스러운")
        }
        if (response.kw._21 != null) {
            keywordFragmentList.add(KeywordAbstractFragment(response.kw._21[0].img))
            keywordTabTitles.add("추상적인")
        }
        if (response.kw._22 != null) {
            keywordFragmentList.add(KeywordRealisticFragment(response.kw._22[0].img))
            keywordTabTitles.add("실제같은")
        }
        if (response.kw._23 != null) {
            keywordFragmentList.add(KeywordExaggeratedFragment(response.kw._23[0].img))
            keywordTabTitles.add("과장된")
        }
        if (response.kw._24 != null) {
            keywordFragmentList.add(KeywordFunFragment(response.kw._24[0].img))
            keywordTabTitles.add("재밌는")
        }
        if (response.kw._25 != null) {
            keywordFragmentList.add(KeywordScaryFragment(response.kw._25[0].img))
            keywordTabTitles.add("무서운")
        }
        if (response.kw._26 != null) {
            keywordFragmentList.add(KeywordBeastlyFragment(response.kw._26[0].img))
            keywordTabTitles.add("짐승같은")
        }
        if (response.kw._27 != null) {
            keywordFragmentList.add(KeywordMysteriousFragment(response.kw._27[0].img))
            keywordTabTitles.add("신비로운")
        }

        // (최애 키워드) 탭 레이아웃 연결 전 뷰페이저 어댑터 설정
        val keywordAdapter = KeywordViewpagerAdapter(activity as FragmentActivity)
        keywordAdapter.fragmentList = keywordFragmentList
        binding?.fragmentHomeKeywordViewpager?.adapter = keywordAdapter

        // (최애 키워드) 탭 레이아웃 연결
        binding?.fragmentHomeKeywordTabLayout?.let {
            binding?.fragmentHomeKeywordViewpager?.let { it1 ->
                TabLayoutMediator(it, it1) { tab, position ->
                    tab.text = keywordTabTitles[position]
                }.attach()
            }
        }

    }

    private fun setBestArtistViewPager(response: GetHomeResponse) {
        // 최고의 아티스트 뷰페이저
        val bestArtistData: MutableList<BestArtist> = loadData(response)
        var bestArtistAdapter = BestArtistViewpagerAdapter()
        bestArtistAdapter.listData = bestArtistData
        binding?.fragmentHomeNewArtistViewpager?.adapter = bestArtistAdapter
    }

    private fun setNewArtistViewPager(response: GetHomeResponse) {
        // 새로운 아티스트 뷰페이저
        val newArtistData: MutableList<NewArtist> = newArtistLoadData(response)
        var newArtistAdapter = NewArtistViewpagerAdapter()
        newArtistAdapter.listData = newArtistData
        binding?.fragmentHomeNewArtistViewpager?.adapter = newArtistAdapter
    }



}