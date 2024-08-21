package code.faizal.androidexpert.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import code.faizal.androidexpert.core.data.source.remote.network.ApiResponse
import code.faizal.androidexpert.core.data.source.remote.network.ApiService
import code.faizal.androidexpert.core.data.source.remote.reponse.MovieResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.detail.MovieDetailResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.genre.GenreResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.review.ListReviewResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.review.ReviewResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.videos.VideosResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteDataSource  @Inject constructor(private val apiService : ApiService){

    @SuppressLint("all")
    fun getAllMovie(genreId : Int):Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val response = apiService.getAllMovie()
            val data = response.results.filter { it.genreIds.contains(genreId) }
            Log.d("MOVIES UHUY","$data")
            if(data.isNotEmpty()){
               emit(ApiResponse.Success(data))
            }else{
                emit(ApiResponse.Empty)
            }

        }catch (e : Exception){
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    @SuppressLint("all")
    fun getAllGenre():Flow<ApiResponse<List<GenreResponse>>> = flow {
        try {
            val response = apiService.getAllGenre()
            val data = response.genresResponse
            if(data.isNotEmpty()){
                emit(ApiResponse.Success(data))
            }else{
                emit(ApiResponse.Empty)
            }

        }catch (e : Exception){
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    @SuppressLint("all")
    fun getMovie(movieId : Int):Flow<ApiResponse<MovieDetailResponse>> = flow {
        try {
            val response = apiService.getMovie(movieId)
            emit(ApiResponse.Success(response))
        }catch (e : Exception){
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    @SuppressLint("all")
    fun getAllReview(movieId : Int):Flow<ApiResponse<ListReviewResponse>> = flow {
        try {
            val response = apiService.getReview(movieId)
            if(response.results.isNotEmpty()){
                emit(ApiResponse.Success(response))
            }else{
                emit(ApiResponse.Empty)
            }

        }catch (e : Exception){
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    @SuppressLint("all")
    fun getAllVideos(movieId : Int):Flow<ApiResponse<List<VideosResponse>>> = flow {
        try {
            val response = apiService.getVideosTrailer(movieId)
            val data = response.results.filter { it.type == "Trailer" }
            if(data.isNotEmpty()){
                emit(ApiResponse.Success(data))
            }else{
                emit(ApiResponse.Empty)
            }
        }catch (e : Exception){
            emit(ApiResponse.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)


}