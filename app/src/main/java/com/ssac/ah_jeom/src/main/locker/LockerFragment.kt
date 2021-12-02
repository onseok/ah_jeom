package com.ssac.ah_jeom.src.main.locker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.FragmentLockerBinding
import com.ssac.ah_jeom.src.main.MainActivity
import com.ssac.ah_jeom.src.main.locker.makeStorage.MakeStorageActivity
import com.ssac.ah_jeom.src.main.locker.models.GetLockerResponse
import com.ssac.ah_jeom.src.main.locker.myImage.MyImageActivity
import com.ssac.ah_jeom.src.profile.ProfileActivity
import com.ssac.ah_jeom.src.profile.myStorage.MyStorageActivity
import com.ssac.ah_jeom.src.search.SearchActivity

class LockerFragment : Fragment(), LockerFragmentView {

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

        binding!!.fragmentLockerSearchButton.setOnClickListener {
            val intent = Intent(requireActivity(), SearchActivity::class.java)
            startActivity(intent)
            (activity as MainActivity).overridePendingTransition(
                R.anim.activity_fade_in,
                R.anim.activity_fade_out
            )
        }

        binding!!.fragmentLockerProfileButton.setOnClickListener {
            val intent = Intent(requireActivity(), ProfileActivity::class.java)
            startActivity(intent)
            (activity as MainActivity).overridePendingTransition(
                R.anim.activity_fade_in,
                R.anim.activity_fade_out
            )
        }

        binding!!.activityArtDetailDownloadButton.setOnClickListener {
            val intent = Intent(requireActivity(), MakeStorageActivity::class.java)
            startActivity(intent)
            (activity as MainActivity).overridePendingTransition(
                R.anim.activity_fade_in,
                R.anim.activity_fade_out
            )
        }

        LockerService(this).tryGetLocker()

        return binding!!.root
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onGetLockerSuccess(response: GetLockerResponse) {
        if(response.isSuccess) {
            when(response.result.storage?.size) {
                0 -> {
                    binding!!.fragmentLockerStorageTextFirst.visibility = View.VISIBLE
                    binding!!.fragmentLockerStorageTextSecond.visibility = View.VISIBLE
                    binding!!.fragmentLockerStorageTextThird.visibility = View.VISIBLE
                    binding!!.fragmentLockerStorageTextFourth.visibility = View.VISIBLE
                    binding!!.fragmentLockerStorageTextFifth.visibility = View.VISIBLE
                }
                1 -> {
                    Glide.with(requireActivity()).load(response.result.storage[0].img).into(binding!!.fragmentLockerStorageImageFirst)
                    binding!!.fragmentLockerStorageImageFirst.alpha = 1F
                    binding!!.fragmentLockerStorageTextFirst.visibility = View.INVISIBLE
                }
                2 -> {
                    Glide.with(requireActivity()).load(response.result.storage[0].img).into(binding!!.fragmentLockerStorageImageFirst)
                    Glide.with(requireActivity()).load(response.result.storage[1].img).into(binding!!.fragmentLockerStorageImageSecond)
                    binding!!.fragmentLockerStorageImageFirst.alpha = 1F
                    binding!!.fragmentLockerStorageImageSecond.alpha = 1F
                    binding!!.fragmentLockerStorageTextFirst.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextSecond.visibility = View.INVISIBLE
                }
                3 -> {
                    Glide.with(requireActivity()).load(response.result.storage[0].img).into(binding!!.fragmentLockerStorageImageFirst)
                    Glide.with(requireActivity()).load(response.result.storage[1].img).into(binding!!.fragmentLockerStorageImageSecond)
                    Glide.with(requireActivity()).load(response.result.storage[2].img).into(binding!!.fragmentLockerStorageImageThird)
                    binding!!.fragmentLockerStorageImageFirst.alpha = 1F
                    binding!!.fragmentLockerStorageImageSecond.alpha = 1F
                    binding!!.fragmentLockerStorageImageThird.alpha = 1F
                    binding!!.fragmentLockerStorageTextFirst.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextSecond.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextThird.visibility = View.INVISIBLE
                }
                4 -> {
                    Glide.with(requireActivity()).load(response.result.storage[0].img).into(binding!!.fragmentLockerStorageImageFirst)
                    Glide.with(requireActivity()).load(response.result.storage[1].img).into(binding!!.fragmentLockerStorageImageSecond)
                    Glide.with(requireActivity()).load(response.result.storage[2].img).into(binding!!.fragmentLockerStorageImageThird)
                    Glide.with(requireActivity()).load(response.result.storage[3].img).into(binding!!.fragmentLockerStorageImageFourth)
                    binding!!.fragmentLockerStorageImageFirst.alpha = 1F
                    binding!!.fragmentLockerStorageImageSecond.alpha = 1F
                    binding!!.fragmentLockerStorageImageThird.alpha = 1F
                    binding!!.fragmentLockerStorageImageFourth.alpha = 1F
                    binding!!.fragmentLockerStorageTextFirst.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextSecond.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextThird.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextFourth.visibility = View.INVISIBLE
                }
                5 -> {
                    Glide.with(requireActivity()).load(response.result.storage[0].img).into(binding!!.fragmentLockerStorageImageFirst)
                    Glide.with(requireActivity()).load(response.result.storage[1].img).into(binding!!.fragmentLockerStorageImageSecond)
                    Glide.with(requireActivity()).load(response.result.storage[2].img).into(binding!!.fragmentLockerStorageImageThird)
                    Glide.with(requireActivity()).load(response.result.storage[3].img).into(binding!!.fragmentLockerStorageImageFourth)
                    Glide.with(requireActivity()).load(response.result.storage[4].img).into(binding!!.fragmentLockerStorageImageFifth)
                    binding!!.fragmentLockerStorageImageFirst.alpha = 1F
                    binding!!.fragmentLockerStorageImageSecond.alpha = 1F
                    binding!!.fragmentLockerStorageImageThird.alpha = 1F
                    binding!!.fragmentLockerStorageImageFourth.alpha = 1F
                    binding!!.fragmentLockerStorageImageFifth.alpha = 1F
                    binding!!.fragmentLockerStorageTextFirst.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextSecond.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextThird.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextFourth.visibility = View.INVISIBLE
                    binding!!.fragmentLockerStorageTextFifth.visibility = View.INVISIBLE
                }
            }
            Glide.with(requireActivity()).load(response.result.myimg?.get(0)?.img).into(binding!!.fragmentLockerMyImageImage)
            binding!!.fragmentLockerMyImageNumberText.text = response.result.myimg?.get(0)?.icount.toString()
        }
    }

    override fun onGetLockerFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
    }
}