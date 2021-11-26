package com.ssac.ah_jeom.src.main.locker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentLockerBinding
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.locker.myImage.MyImageActivity
import com.ssac.ah_jeom.src.profile.myStorage.MyStorageActivity

class LockerFragment : Fragment() {

    private var binding: FragmentLockerBinding? = null

    // 뷰가 생성되었을 때 - 프레그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLockerBinding.inflate(inflater, container, false)

        binding!!.fragmentLockerMyImageButton.setOnClickListener {
            startActivity(Intent(requireActivity(), MyImageActivity::class.java))
            (activity as MainActivity).overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        binding!!.fragmentLockerMyStorageButton.setOnClickListener {
            startActivity(Intent(requireActivity(), MyStorageActivity::class.java))
            (activity as MainActivity).overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
        }

        return binding!!.root
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}