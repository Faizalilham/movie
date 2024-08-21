package code.faizal.androidexpert.presentation.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import code.faizal.androidexpert.core.data.Resource
import code.faizal.androidexpert.core.domain.model.Genre
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val movieUseCase : MovieUseCase): ViewModel() {

    private val _movies: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()
    val getAllMovie: LiveData<Resource<List<Movie>>> = _movies

    private val _genres: MutableLiveData<Resource<List<Genre>>> = MutableLiveData()
    val getAllGenres: LiveData<Resource<List<Genre>>> = _genres

    init {
        getAllGenre()
    }

    fun getAllMovie(genreId : Int) {
        movieUseCase.movies(genreId).asLiveData().observeForever {
            _movies.postValue(it)
        }
    }

    private fun getAllGenre() {
        movieUseCase.genres().asLiveData().observeForever {
            _genres.postValue(it)
        }
    }

}