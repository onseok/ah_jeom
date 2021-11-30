package com.ssac.ah_jeom.src.detail.artistDetail.artistReview

import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.GetArtistReviewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistReviewRetrofitInterface {

    @GET("/app/users/{userId}/artist/{artistId}/reviews")
    fun getArtistReview(@Path("userId") userId: Int, @Path("artistId") artistId: Int, @Query("cursor") cursor: String?): Call<GetArtistReviewResponse>

}