package com.ssac.ah_jeom.src.main.locker

import com.ssac.ah_jeom.src.main.locker.models.GetLockerResponse

interface LockerFragmentView {

    fun onGetLockerSuccess(response: GetLockerResponse)

    fun onGetLockerFailure(message: String)

}