package com.ssac.ah_jeom.src.main.subscribe

import com.ssac.ah_jeom.src.main.subscribe.models.GetSubscribeResponse


interface SubscribeFragmentView {

    fun onGetSubscribeSuccess(response: GetSubscribeResponse)

    fun onGetSubscribeFailure(message: String)

}