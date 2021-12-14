package com.ssac.ah_jeom.util

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

abstract class PermissionActivity: AppCompatActivity() {
    abstract fun permissionGranted(requestCode: Int)
    abstract fun permissionDenied(requestCode: Int)

    fun requirePermissions(permissions: Array<String>, requestCode: Int) {
        // 안드로이드 6.0(마시멜로우) 미만이면 permissionGranted() 메서드를 호출하면서 전달받은 requestCode를 함께 전달
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGranted(requestCode)
        }
        // 권한 체크를 해야하는 버전이라면
        else {
            // 모두 권한이 승인된 경우에만 true
            val isAllPermissionGranted = permissions.all {
                checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
            }
            if (isAllPermissionGranted) {
                // 승인이면 프로그램 진행
                permissionGranted(requestCode)
            }
            else {
                // 미승인이면 권한 요청
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            }
        }
    }

    // 사용자가 권한을 승인하거나 거부한 다음에 호출되는 메서드 (오버라이드)
    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            permissionGranted(requestCode)
        }
        else {
            permissionDenied(requestCode)
        }
    }
}