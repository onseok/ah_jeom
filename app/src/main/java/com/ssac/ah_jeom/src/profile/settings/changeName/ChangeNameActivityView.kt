package com.ssac.ah_jeom.src.profile.settings.changeName

import com.ssac.ah_jeom.src.profile.settings.changeName.models.GetNameResponse
import com.ssac.ah_jeom.src.profile.settings.changeName.models.PatchNameResponse


interface ChangeNameActivityView {

    fun onGetNameSuccess(response: GetNameResponse)

    fun onGetNameFailure(message: String)

    fun onPatchNameSuccess(response: PatchNameResponse)

    fun onPatchNameFailure(message: String)

}