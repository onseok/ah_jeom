package com.ssac.ah_jeom.src.detail.artistDetail

import com.ssac.ah_jeom.src.detail.artistDetail.models.GetArtistDetailResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.PatchSubscribeResponse
import com.ssac.ah_jeom.src.detail.artistDetail.models.PostSubscribeResponse


interface ArtistDetailActivityView {

    fun onGetArtistDetailSuccess(response: GetArtistDetailResponse)

    fun onGetArtistDetailFailure(message: String)

    fun onPostSubscribeSuccess(response: PostSubscribeResponse)

    fun onPostSubscribeFailure(message: String)

    fun onPatchSubscribeSuccess(response: PatchSubscribeResponse)

    fun onPatchSubscribeFailure(message: String)

}