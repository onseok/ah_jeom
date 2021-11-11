package com.ssac.ah_jeom.src.userInfo.keyword

import com.ssac.ah_jeom.src.userInfo.keyword.models.PostKeywordResponse

interface KeywordActivityView {

    fun onPostKeywordSuccess(response: PostKeywordResponse)

    fun onPostKeywordFailure(message: String)
}