package com.ssac.ah_jeom.src.userInfo.interests

import com.ssac.ah_jeom.src.userInfo.interests.models.PostInterestsResponse

interface InterestsActivityView {

    fun onPostInterestsSuccess(response: PostInterestsResponse)

    fun onPostInterestsFailure(message: String)
}