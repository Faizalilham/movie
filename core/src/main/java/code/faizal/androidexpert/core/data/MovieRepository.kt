package code.faizal.androidexpert.core.data

import code.faizal.androidexpert.core.data.source.local.LocalDataSource
import code.faizal.androidexpert.core.data.source.remote.RemoteDataSource
import code.faizal.androidexpert.core.data.source.remote.network.ApiResponse
import code.faizal.androidexpert.core.data.source.remote.reponse.MovieResponse
import code.faizal.androidexpert.core.domain.model.Genre
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.domain.model.MovieDetail
import code.faizal.androidexpert.core.domain.model.Review
import code.faizal.androidexpert.core.domain.model.Videos
import code.faizal.androidexpert.core.domain.repository.IMovieRepository
import code.faizal.androidexpert.core.utils.AppExecutors
import code.faizal.androidexpert.core.utils.DataMapper
import code.faizal.androidexpert.core.utils.DataMapper.mapFromListReviewResponseToDomain
import code.faizal.androidexpert.core.utils.DataMapper.mapFromListVideoResponseToDomain
import code.faizal.androidexpert.core.utils.DataMapper.mapFromMovieDetailResponseToDomain
import code.faizal.androidexpert.core.utils.DataMapper.mapFromResponseToDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource,
    private val appExecutors : AppExecutors
): IMovieRepository {

    override fun movies(genreId : Int): Flow<Resource<List<Movie>>> {
       return  remoteDataSource.getAllMovie(genreId)
           .map { apiResponse ->
               when (apiResponse) {
                   is ApiResponse.Success -> Resource.Success(mapFromResponseToDomain(apiResponse.data))
                   is ApiResponse.Empty -> Resource.Loading()
                   is ApiResponse.Error -> Resource.Error(apiResponse.message)
               }
           }.flowOn(Dispatchers.IO)
    }

    override fun genres(): Flow<Resource<List<Genre>>>{
        return remoteDataSource.getAllGenre()
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> Resource.Success(DataMapper.mapFromListGenreResponseToDomain(apiResponse.data))
                    is ApiResponse.Empty -> Resource.Loading()
                    is ApiResponse.Error -> Resource.Error(apiResponse.message)
                }
            }.flowOn(Dispatchers.IO)
    }

    override fun detailMovie(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        remoteDataSource.getMovie(movieId).collect { detail ->
            when(detail) {
                is ApiResponse.Success -> emit(Resource.Success(mapFromMovieDetailResponseToDomain(detail.data)))
                is ApiResponse.Empty -> {}
                is ApiResponse.Error ->{}
            }
        }
    }.flowOn(Dispatchers.IO)



    override fun reviews(movieId: Int): Flow<Resource<Review>> {
        return remoteDataSource.getAllReview(movieId)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> Resource.Success(mapFromListReviewResponseToDomain(apiResponse.data))
                    is ApiResponse.Empty -> Resource.Loading()
                    is ApiResponse.Error -> Resource.Error(apiResponse.message)
                }
            }.flowOn(Dispatchers.IO)
    }

    override fun videos(movieId: Int): Flow<Resource<List<Videos>>> {
        return remoteDataSource.getAllVideos(movieId)
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> Resource.Success(mapFromListVideoResponseToDomain(apiResponse.data))
                    is ApiResponse.Empty -> Resource.Loading()
                    is ApiResponse.Error -> Resource.Error(apiResponse.message)
                }
            }.flowOn(Dispatchers.IO)
    }

    override fun favorites(): Flow<List<Movie>> {
       return localDataSource.getAllFavorite().map {
           DataMapper.mapFromEntitiesToDomain(it)
       }
    }

    override fun setFavorite(movie: Movie, isFavorite: Boolean) {
       val movieEntity = DataMapper.mapFromDomainToEntity(movie)
        appExecutors.diskIO().execute{
            localDataSource.updateMovieFavorite(movieEntity, isFavorite)
        }
    }
}