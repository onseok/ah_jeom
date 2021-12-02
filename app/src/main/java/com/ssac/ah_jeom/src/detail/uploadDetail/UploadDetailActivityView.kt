package com.ssac.ah_jeom.src.detail.uploadDetail

import com.ssac.ah_jeom.src.detail.uploadDetail.models.PostArtResponse

interface UploadDetailActivityView {

    fun onPostArtSuccess(response: PostArtResponse)

    fun onPostArtFailure(message: String)

}