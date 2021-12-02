package com.ssac.ah_jeom.src.main.upload

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseFragment
import com.ssac.ah_jeom.databinding.FragmentLockerBinding
import com.ssac.ah_jeom.databinding.FragmentUploadBinding
import com.ssac.ah_jeom.src.detail.uploadDetail.UploadDetailActivity
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.locker.LockerFragmentView
import com.ssac.ah_jeom.src.main.locker.LockerService
import com.ssac.ah_jeom.src.main.locker.models.GetLockerResponse
import com.ssac.ah_jeom.src.main.locker.myImage.MyImageActivity
import com.ssac.ah_jeom.src.profile.ProfileActivity
import com.ssac.ah_jeom.src.profile.myStorage.MyStorageActivity
import com.ssac.ah_jeom.src.search.SearchActivity

class UploadFragment : Fragment() {

    private var binding: FragmentUploadBinding? = null

    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUploadBinding.inflate(inflater, container, false)

        binding!!.fragmentUploadArtLayout.setOnClickListener {
            val intent = Intent(requireActivity(), UploadDetailActivity::class.java)
            startActivity(intent)
            (activity as MainActivity).overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }


        return binding!!.root
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }




}