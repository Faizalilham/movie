package code.faizal.androidexpert.core.domain.usecase.impl

import android.annotation.SuppressLint
import code.faizal.androidexpert.core.data.Resource
import code.faizal.androidexpert.core.domain.model.Genre
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.domain.model.MovieDetail
import code.faizal.androidexpert.core.domain.model.Review
import code.faizal.androidexpert.core.domain.model.Videos
import code.faizal.androidexpert.core.domain.repository.IMovieRepository
import code.faizal.androidexpert.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@SuppressLint("all")
class MovieUseCaseImpl @Inject constructor(
    private val iMovieRepository: IMovieRepository): MovieUseCase {
    override fun movies(genreId : Int): Flow<Resource<List<Movie>>> {
        return iMovieRepository.movies(genreId)
    }

    override fun genres(): Flow<Resource<List<Genre>>> {
        return iMovieRepository.genres()
    }

    override fun detailMovie(movieId : Int): Flow<Resource<MovieDetail>> {
        return iMovieRepository.detailMovie(movieId)
    }

    override fun reviews(movieId: Int): Flow<Resource<Review>> {
        return iMovieRepository.reviews(movieId)
    }

    override fun videos(movieId: Int): Flow<Resource<List<Videos>>> {
       return iMovieRepository.videos(movieId)
    }

    override fun favorites(): Flow<List<Movie>> {
       return iMovieRepository.favorites()
    }

    override fun setFavorite(movie: Movie, isFavorite: Boolean) {
        return iMovieRepository.setFavorite(movie, isFavorite)
    }
}