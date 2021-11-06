package com.ssac.ah_jeom.src.main

import android.os.Bundle
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.src.main.home.HomeFragment
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityMainBinding
import com.ssac.ah_jeom.src.main.locker.LockerFragment
import com.ssac.ah_jeom.src.main.peek.PeekFragment
import com.ssac.ah_jeom.src.main.subscribe.SubscribeFragment
import com.ssac.ah_jeom.src.main.upload.UploadFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

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
}