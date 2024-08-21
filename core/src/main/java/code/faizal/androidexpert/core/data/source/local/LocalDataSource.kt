package code.faizal.androidexpert.core.data.source.local


import code.faizal.androidexpert.core.data.source.local.entity.MovieEntity
import code.faizal.androidexpert.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor (private val movieDao : MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.movies()

    fun getAllFavorite():Flow<List<MovieEntity>> = movieDao.favorites()

    suspend fun insertMovie(movies : List<MovieEntity>) = movieDao.insertMovie(movies)

    fun updateMovieFavorite(movieEntity : MovieEntity, isFavorite : Boolean){

        movieEntity.isFavorite = isFavorite

        movieDao.updateMovie(movieEntity)
    }


}