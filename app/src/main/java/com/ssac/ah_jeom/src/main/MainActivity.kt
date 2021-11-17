package com.ssac.ah_jeom.src.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kakao.sdk.common.util.Utility
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.src.main.home.HomeFragment
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityMainBinding
import com.ssac.ah_jeom.src.main.locker.LockerFragment
import com.ssac.ah_jeom.src.main.peek.PeekFragment
import com.ssac.ah_jeom.src.main.subscribe.SubscribeFragment
import com.ssac.ah_jeom.src.main.upload.UploadFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    //뒤로가기 연속 클릭 대기 시간
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBtmNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_btm_nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_btm_nav_peek -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, PeekFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_btm_nav_upload -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, UploadFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_btm_nav_subscribe -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SubscribeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_main_btm_nav_locker -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        // 2초내 다시 클릭하면 앱 종료
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            moveTaskToBack(true)
            finish()
            android.os.Process.killProcess(android.os.Process.myPid())
            return
        }
        // 처음 클릭 메시지
        showCustomToast("앱을 종료합니다.")
        backPressedTime = System.currentTimeMillis()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}