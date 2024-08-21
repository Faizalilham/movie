package code.faizal.androidexpert.core.domain.repository

import code.faizal.androidexpert.core.data.Resource
import code.faizal.androidexpert.core.domain.model.Genre
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.domain.model.MovieDetail
import code.faizal.androidexpert.core.domain.model.Review
import code.faizal.androidexpert.core.domain.model.Videos
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun movies(genreId : Int): Flow<Resource<List<Movie>>>

    fun genres () : Flow<Resource<List<Genre>>>

    fun detailMovie(movieId : Int): Flow<Resource<MovieDetail>>

    fun reviews(movieId : Int) : Flow<Resource<Review>>

    fun videos(movieId : Int) : Flow<Resource<List<Videos>>>

    fun favorites(): Flow<List<Movie>>

    fun setFavorite(movie : Movie, isFavorite : Boolean)
}