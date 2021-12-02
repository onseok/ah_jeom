package com.ssac.ah_jeom.src.profile.settings

import com.ssac.ah_jeom.src.profile.settings.models.GetSettingsResponse

interface SettingsActivityView {

    fun onGetSettingsSuccess(response: GetSettingsResponse)

    fun onGetSettingsFailure(message: String)

}