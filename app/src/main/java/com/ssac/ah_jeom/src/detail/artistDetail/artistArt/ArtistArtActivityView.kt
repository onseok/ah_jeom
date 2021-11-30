package com.ssac.ah_jeom.src.detail.artistDetail.artistArt

import com.ssac.ah_jeom.src.detail.artistDetail.artistArt.models.GetArtistArtResponse

interface ArtistArtActivityView {

    fun onGetArtistArtSuccess(response: GetArtistArtResponse)

    fun onGetArtistArtFailure(message: String)

}