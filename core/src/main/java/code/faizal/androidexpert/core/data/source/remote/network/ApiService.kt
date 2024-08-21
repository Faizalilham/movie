package code.faizal.androidexpert.core.data.source.remote.network

import code.faizal.androidexpert.core.data.source.remote.reponse.ListMovieResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.MovieResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.detail.MovieDetailResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.genre.ListGenreResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.review.ListReviewResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.videos.ListVideoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("discover/movie")
    suspend fun getAllMovie(): ListMovieResponse<MovieResponse>

    @GET("genre/movie/list")
    suspend fun getAllGenre(): ListGenreResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId : Int
    ): MovieDetailResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getReview(
        @Path("movie_id") movieId : Int
    ): ListReviewResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideosTrailer(
        @Path("movie_id") movieId : Int
    ): ListVideoResponse

}