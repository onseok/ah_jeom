package com.ssac.ah_jeom.src.main.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentHomeBinding
import com.ssac.ah_jeom.src.detail.PeekSavedActivity
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.home.adapter.*
import com.ssac.ah_jeom.src.main.home.fragments.*
import com.ssac.ah_jeom.src.main.home.models.*
import com.ssac.ah_jeom.src.profile.ProfileActivity
import com.ssac.ah_jeom.src.search.SearchActivity
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), HomeFragmentView {

    private var binding: FragmentHomeBinding? = null

    private var isRotated = false
    private var isRotatedKeyword = false

    private var numBanner = 3
    private var currentPosition = 2
    private var myHandler = MyHandler()
    private val intervalTime = 5000.toLong() // 몇초 간격으로 페이지를 넘길것인지 (5000 = 5.0초)

    private val listData: MutableList<BestArtist> = mutableListOf()
    private val newArtistListData: MutableList<NewArtist> = mutableListOf()

    private val addInterestsData: MutableList<UpdateInterestsRecyclerData> = mutableListOf()
    private val deleteInterestsData: MutableList<UpdateInterestsRecyclerData> = mutableListOf()

    private val addKeywordData: MutableList<UpdateKeywordRecyclerData> = mutableListOf()
    private val deleteKeywordData: MutableList<UpdateKeywordRecyclerData> = mutableListOf()

    private val modalOpen: Animation by lazy {
        AnimationUtils.loadAnimation(
            activity,
            R.anim.modal_open
        )
    }

    private val modalClose: Animation by lazy {
        AnimationUtils.loadAnimation(
            activity,
            R.anim.modal_close
        )
    }

    private val modalOpenKeyword: Animation by lazy {
        AnimationUtils.loadAnimation(
            activity,
            R.anim.modal_open
        )
    }

    private val modalCloseKeyword: Animation by lazy {
        AnimationUtils.loadAnimation(
            activity,
            R.anim.modal_close
        )
    }

    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 홈 화면 API 연동
        HomeService(this).tryGetHome()

        // 메인 이미지 하단 둥글게
        with(binding!!.fragmentHomeBannerNestedScrollableHost) {
            background = resources.getDrawable(R.drawable.image_bottom_round, null)
            clipToOutline
        }

        binding?.fragmentHomeInterestsCurrentText?.setOnClickListener {
            // 0.22초에 걸쳐서 180도 회전
            when (isRotated) {
                false -> {
                    showUpdateInterestsView()
                    isRotated = true
                }
                true -> {
                    hideUpdateInterestsView()
                    isRotated = false
                }
            }
        }

        binding?.fragmentHomeKeywordCurrentText?.setOnClickListener {
            // 0.22초에 걸쳐서 180도 회전
            when (isRotatedKeyword) {
                false -> {
                    showUpdateKeywordView()
                    isRotatedKeyword = true
                }
                true -> {
                    hideUpdateKeywordView()
                    isRotatedKeyword = false
                }
            }
        }

        binding?.fragmentHomeSearchButton?.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
            (activity as MainActivity).overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding?.fragmentHomeProfileButton?.setOnClickListener {
            startActivity(Intent(requireActivity(), ProfileActivity::class.java))
            (activity as MainActivity).overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        // 상단 status bar만 투명하게!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (activity as MainActivity).window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            (activity as MainActivity).window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            (activity as MainActivity).window.statusBarColor = 0x00000000 // transparent
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            (activity as MainActivity).window.addFlags(flags)
        }
        (activity as MainActivity).window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // 여기까지


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

        binding?.currentBannerTextView?.text ="01"
        binding?.totalBannerTextView?.text = "%02d".format(numBanner)

        // 현재 몇 번째 배너인지 보여주는 숫자를 변경함
        val apply = binding!!.fragmentHomeBannerViewpager.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding?.currentBannerTextView?.text =
                        "%02d".format((position % 3) + 1) // position이 0부터 시작해서 + 문자열 포매팅
                }

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

        if (response.field._12 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, MoldingFragment(response.field._12[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "조형"
            addInterestsData.add(UpdateInterestsRecyclerData("조형"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("조형"))
        }

        if (response.field._11 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, ThreeDimensionsFragment(response.field._11[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "3D"
            addInterestsData.add(UpdateInterestsRecyclerData("3D"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("3D"))
        }

        if (response.field._10 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, ArchitectureFragment(response.field._10[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "건축"
            addInterestsData.add(UpdateInterestsRecyclerData("건축"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("건축"))
        }

        if (response.field._9 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, PersonFragment(response.field._9[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "인물"
            addInterestsData.add(UpdateInterestsRecyclerData("인물"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("인물"))
        }

        if (response.field._8 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, NaturalFragment(response.field._8[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "자연"
            addInterestsData.add(UpdateInterestsRecyclerData("자연"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("자연"))
        }

        if (response.field._7 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, PhotoFragment(response.field._7[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "사진"
            addInterestsData.add(UpdateInterestsRecyclerData("사진"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("사진"))
        }

        if (response.field._6 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, SculptureFragment(response.field._6[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "조각"
            addInterestsData.add(UpdateInterestsRecyclerData("조각"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("조각"))
        }

        if (response.field._5 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, CartoonFragment(response.field._5[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "만화"
            addInterestsData.add(UpdateInterestsRecyclerData("만화"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("만화"))
        }

        if (response.field._4 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, AbstractFragment(response.field._4[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "추상"
            addInterestsData.add(UpdateInterestsRecyclerData("추상"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("추상"))
        }

        if (response.field._3 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, ModernArtFragment(response.field._3[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "현대미술"
            addInterestsData.add(UpdateInterestsRecyclerData("현대미술"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("현대미술"))
        }

        if (response.field._2 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, IllustrationFragment(response.field._2[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "일러스트"
            addInterestsData.add(UpdateInterestsRecyclerData("일러스트"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("일러스트"))
        }

        if (response.field._1 != null) {
            // 우선 첫번째 이미지만 서버에서 받아오자! (뷰페이저 구현은 나중에 생각)
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_interests_frame_layout, GraphicFragment(response.field._1[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeInterestsCurrentText?.text = "그래픽"
            addInterestsData.add(UpdateInterestsRecyclerData("그래픽"))
        }
        else {
            deleteInterestsData.add(UpdateInterestsRecyclerData("그래픽"))
        }

        // 회원가입시 선택한 관심분야 레이아웃 데이터 설정
        setAddInterestsView()
        
        // 회원가입시 선택하지 못한 나머지 관심분야 레이아웃 데이터 설정
        setDeleteInterestsView()

    }

    private fun setKeyword(response: GetHomeResponse) {

        if (response.kw._27 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordMysteriousFragment(response.kw._27[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "신비로운"
            addKeywordData.add(UpdateKeywordRecyclerData("신비로운"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("신비로운"))
        }

        if (response.kw._26 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordBeastlyFragment(response.kw._26[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "짐승같은"
            addKeywordData.add(UpdateKeywordRecyclerData("짐승같은"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("짐승같은"))
        }

        if (response.kw._25 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordScaryFragment(response.kw._25[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "무서운"
            addKeywordData.add(UpdateKeywordRecyclerData("무서운"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("무서운"))
        }

        if (response.kw._24 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordFunFragment(response.kw._24[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "재밌는"
            addKeywordData.add(UpdateKeywordRecyclerData("재밌는"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("재밌는"))
        }

        if (response.kw._23 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordExaggeratedFragment(response.kw._23[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "과장된"
            addKeywordData.add(UpdateKeywordRecyclerData("과장된"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("과장된"))
        }


        if (response.kw._22 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordRealisticFragment(response.kw._22[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "실제같은"
            addKeywordData.add(UpdateKeywordRecyclerData("실제같은"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("실제같은"))
        }

        if (response.kw._21 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordAbstractFragment(response.kw._21[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "추상적인"
            addKeywordData.add(UpdateKeywordRecyclerData("추상적인"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("추상적인"))
        }

        if (response.kw._20 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordHolyFragment(response.kw._20[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "성스러운"
            addKeywordData.add(UpdateKeywordRecyclerData("성스러운"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("성스러운"))
        }

        if (response.kw._19 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordBeautifulFragment(response.kw._19[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "아름다운"
            addKeywordData.add(UpdateKeywordRecyclerData("아름다운"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("아름다운"))
        }

        if (response.kw._18 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordCuteFragment(response.kw._18[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "귀여운"
            addKeywordData.add(UpdateKeywordRecyclerData("귀여운"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("귀여운"))
        }

        if (response.kw._17 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordVividFragment(response.kw._17[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "원색적인"
            addKeywordData.add(UpdateKeywordRecyclerData("원색적인"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("원색적인"))
        }

        if (response.kw._16 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordComfortableFragment(response.kw._16[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "편안한"
            addKeywordData.add(UpdateKeywordRecyclerData("편안한"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("편안한"))
        }

        if (response.kw._15 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordStrongFragment(response.kw._15[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "강렬한"
            addKeywordData.add(UpdateKeywordRecyclerData("강렬한"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("강렬한"))
        }

        if (response.kw._14 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordFancyFragment(response.kw._14[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "화려한"
            addKeywordData.add(UpdateKeywordRecyclerData("화려한"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("화려한"))
        }

        if (response.kw._13 != null) {
            val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction().add(R.id.fragment_home_keyword_frame_layout, KeywordSimpleFragment(response.kw._13[0].img))
            transaction.addToBackStack(null)
            transaction.commit()
            binding?.fragmentHomeKeywordCurrentText?.text = "심플한"
            addKeywordData.add(UpdateKeywordRecyclerData("심플한"))
        }
        else {
            deleteKeywordData.add(UpdateKeywordRecyclerData("심플한"))
        }

        // 회원가입시 선택한 관심 키워드 레이아웃 데이터 설정
        setAddKeywordView()

        // 회원가입시 선택하지 못한 나머지 관심 키워드 레이아웃 데이터 설정
        setDeleteKeywordView()

    }

    private fun setBestArtistViewPager(response: GetHomeResponse) {
        // 최고의 아티스트 뷰페이저
        val bestArtistData: MutableList<BestArtist> = loadData(response)
        var bestArtistAdapter = BestArtistViewpagerAdapter()
        bestArtistAdapter.listData = bestArtistData
        binding?.fragmentHomeBestArtistViewpager?.adapter = bestArtistAdapter

        // 뷰페이저 인디케이터
        val dotsIndicator = binding?.bestArtistDotsIndicator
        val viewPager = binding?.fragmentHomeBestArtistViewpager
        viewPager?.adapter = bestArtistAdapter
        dotsIndicator?.setViewPager2(viewPager!!)
    }

    private fun setNewArtistViewPager(response: GetHomeResponse) {
        // 새로운 아티스트 뷰페이저
        val newArtistData: MutableList<NewArtist> = newArtistLoadData(response)
        var newArtistAdapter = NewArtistViewpagerAdapter()
        newArtistAdapter.listData = newArtistData
        binding?.fragmentHomeNewArtistViewpager?.adapter = newArtistAdapter

        // 뷰페이저 인디케이터
        val dotsIndicator = binding?.newArtistDotsIndicator
        val viewPager = binding?.fragmentHomeNewArtistViewpager
        viewPager?.adapter = newArtistAdapter
        dotsIndicator?.setViewPager2(viewPager!!)
    }

    private fun setAddInterestsView() {
        addInterestsData.reverse()
        val data: MutableList<UpdateInterestsRecyclerData> = addInterestsData
        var adapter = InterestsAddRecyclerAdapter(requireActivity())
        adapter.listData = data
        binding?.fragmentHomeInterestsUpdateAddRecyclerView?.adapter = adapter
        binding?.fragmentHomeInterestsUpdateAddRecyclerView?.layoutManager = GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()

    }

    private fun setDeleteInterestsView() {
        deleteInterestsData.reverse()
        val data: MutableList<UpdateInterestsRecyclerData> = deleteInterestsData
        var adapter = InterestsDeleteRecyclerAdapter(requireActivity())
        adapter.listData = data
        binding?.fragmentHomeInterestsUpdateDeleteRecyclerView?.adapter = adapter
        binding?.fragmentHomeInterestsUpdateDeleteRecyclerView?.layoutManager = GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }


    private fun showUpdateInterestsView() {
        binding?.fragmentHomeInterestsDirectionButton?.animate()?.rotationBy(180f)?.duration = 220
        binding?.fragmentHomeInterestsUpdateLayout?.visibility = View.VISIBLE
        binding?.fragmentHomeInterestsUpdateLayout?.startAnimation(modalOpen)
        isRotated = true
    }

    private fun hideUpdateInterestsView() {
        binding?.fragmentHomeInterestsDirectionButton?.animate()?.rotationBy(-180f)?.duration = 220
        binding?.fragmentHomeInterestsUpdateLayout?.visibility = View.GONE
        binding?.fragmentHomeInterestsUpdateLayout?.startAnimation(modalClose)
        isRotated = false
    }

    //

    private fun setAddKeywordView() {
        addKeywordData.reverse()
        val data: MutableList<UpdateKeywordRecyclerData> = addKeywordData
        var adapter = KeywordAddRecyclerAdapter(requireActivity())
        adapter.listData = data
        binding?.fragmentHomeKeywordUpdateAddRecyclerView?.adapter = adapter
        binding?.fragmentHomeKeywordUpdateAddRecyclerView?.layoutManager = GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }

    private fun setDeleteKeywordView() {
        deleteKeywordData.reverse()
        val data: MutableList<UpdateKeywordRecyclerData> = deleteKeywordData
        var adapter = KeywordDeleteRecyclerAdapter(requireActivity())
        adapter.listData = data
        binding?.fragmentHomeKeywordUpdateDeleteRecyclerView?.adapter = adapter
        binding?.fragmentHomeKeywordUpdateDeleteRecyclerView?.layoutManager = GridLayoutManager(requireActivity(), 3, GridLayoutManager.VERTICAL, false)

        adapter.notifyDataSetChanged()
    }


    private fun showUpdateKeywordView() {
        binding?.fragmentHomeKeywordDirectionButton?.animate()?.rotationBy(180f)?.duration = 220
        binding?.fragmentHomeKeywordUpdateLayout?.visibility = View.VISIBLE
        binding?.fragmentHomeKeywordUpdateLayout?.startAnimation(modalOpenKeyword)
        isRotatedKeyword = true
    }

    private fun hideUpdateKeywordView() {
        binding?.fragmentHomeKeywordDirectionButton?.animate()?.rotationBy(-180f)?.duration = 220
        binding?.fragmentHomeKeywordUpdateLayout?.visibility = View.GONE
        binding?.fragmentHomeKeywordUpdateLayout?.startAnimation(modalCloseKeyword)
        isRotatedKeyword = false
    }

}