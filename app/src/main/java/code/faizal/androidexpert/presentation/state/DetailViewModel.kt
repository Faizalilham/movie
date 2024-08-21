package code.faizal.androidexpert.presentation.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import code.faizal.androidexpert.core.data.Resource
import code.faizal.androidexpert.core.domain.model.Genre
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.domain.model.MovieDetail
import code.faizal.androidexpert.core.domain.model.Review
import code.faizal.androidexpert.core.domain.model.Videos
import code.faizal.androidexpert.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val  movieUseCase: MovieUseCase
): ViewModel() {

    private val _reviews: MutableLiveData<Resource<Review>> = MutableLiveData()
    val getAllReview: LiveData<Resource<Review>> = _reviews

    private val _videos: MutableLiveData<Resource<List<Videos>>> = MutableLiveData()
    val getAllVideo: LiveData<Resource<List<Videos>>> = _videos

    private val _movie: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()
    val getMovie: LiveData<Resource<MovieDetail>> = _movie

    fun movieDetail(movieId : Int){
        movieUseCase.detailMovie(movieId).asLiveData().observeForever {
            _movie.postValue(it)
        }
    }

    fun setFavorite(movie : Movie, isFavorite: Boolean){
        movieUseCase.setFavorite(movie,isFavorite)
    }

    fun getReviews(movieId : Int) {
        movieUseCase.reviews(movieId).asLiveData().observeForever {
            _reviews.postValue(it)
        }
    }

    fun getVideos(movieId : Int) {
        movieUseCase.videos(movieId).asLiveData().observeForever {
            _videos.postValue(it)
        }
    }

}