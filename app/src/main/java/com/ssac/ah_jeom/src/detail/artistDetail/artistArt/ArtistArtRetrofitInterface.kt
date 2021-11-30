package com.ssac.ah_jeom.src.detail.artistDetail.artistArt

import com.ssac.ah_jeom.src.detail.artistDetail.artistArt.models.GetArtistArtResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistArtRetrofitInterface {

    @GET("/app/users/{userId}/artist/{artistId}/artworks")
    fun getArtistArt(@Path("userId") userId: Int, @Path("artistId") artistId: Int): Call<GetArtistArtResponse>

}