package com.ssac.ah_jeom.src.main.peek

import android.content.Intent
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentPeekBinding
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.peek.adapter.PeekMainViewpagerAdapter
import com.ssac.ah_jeom.src.main.peek.models.GetPeekResponse
import com.ssac.ah_jeom.src.main.peek.models.PeekMainViewpagerData
import com.ssac.ah_jeom.src.profile.myPeek.MyPeekActivity

class PeekFragment : Fragment(), PeekFragmentView {

    private var binding: FragmentPeekBinding? = null

    private val illustrationData: MutableList<PeekMainViewpagerData> = mutableListOf()

    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPeekBinding.inflate(inflater, container, false)

        binding!!.fragmentPeekMainViewPager.adapter = PeekMainViewpagerAdapter(requireActivity()) // 어댑터 생성
        binding!!.fragmentPeekMainViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로

        binding!!.fragmentPeekSavedStorageButton.setOnClickListener {
            startActivity(Intent(requireActivity(), MyPeekActivity::class.java))
            (activity as MainActivity).overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        setSubscribeIllustrationViewpager()

        val Pair1 = Pair<View,String>(
            binding!!.fragmentPeekImageView, "peek_image")

        val Pair2 = Pair<View,String>(
            binding!!.fragmentPeekImageView, "peek_image")

        // TODO 옅보기 상단 큰 이미지 선택했을때
//        binding!!.fragmentPeekImageView.setOnClickListener {
//            val intent = Intent(requireActivity(), PeekDetailActivity::class.java)
//            var options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(requireActivity())
//            startActivity(intent, options.toBundle())
//        }

        Glide.with(this).load(R.drawable.fragment_peek_profile_image_temp).circleCrop().into(binding!!.fragmentPeekMainProfileImage)

        PeekService(this).tryGetPeek()

        return binding!!.root
    }

    private fun setSubscribeIllustrationViewpager() {
        setIllustrationImageData()

        val data: MutableList<PeekMainViewpagerData> = illustrationData
        var adapter = PeekMainViewpagerAdapter(requireActivity())
        adapter.item = data
        binding!!.fragmentPeekMainViewPager.adapter = adapter
        binding!!.fragmentPeekMainViewPager.orientation =
            ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        adapter.notifyDataSetChanged()

        // 뷰페이저 인디케이터
        val dotsIndicator = binding?.fragmentPeekIllustrationIndicator
        val viewPager = binding?.fragmentPeekMainViewPager
        viewPager?.adapter = adapter
        dotsIndicator?.setViewPager2(viewPager!!)
    }

    private fun setIllustrationImageData() {
        illustrationData.add(PeekMainViewpagerData(R.drawable.fragment_peek_best_storage, "인기 메이커", "베스트 보관함 가기"))
        illustrationData.add(PeekMainViewpagerData(R.drawable.fragment_peek_new_storage, "새싹이 퐁퐁퐁", "NEW 보관함 가기"))
        illustrationData.add(PeekMainViewpagerData(R.drawable.fragment_peek_soaring_storage, "슈퍼맨 빠워", "급상승 보관함 가기"))
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onGetPeekSuccess(response: GetPeekResponse) {
        if (response.isSuccess) {
            binding!!.fragmentPeekMainTitle.text = response.result?.title // 보관함 제목
            Glide.with(this).load(response.result?.img).into(binding!!.fragmentPeekImageView) // 보관함 이미지
            binding!!.fragmentPeekMainProfileName.text = response.result?.nickname //유저 이름
            Glide.with(this).load(response.result?.profile).circleCrop().into(binding!!.fragmentPeekMainProfileImage) // 유저 프사
            binding!!.fragmentPeekMainProfileDownloadNumber.text = "${response.result?.save}회 다운" // 보관함 다운수(저장수)
        }
    }

    override fun onGetPeekFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
    }

}