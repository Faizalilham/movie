package code.faizal.androidexpert.favorite.presentation.state


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import code.faizal.androidexpert.core.domain.model.Movie
import code.faizal.androidexpert.core.domain.usecase.MovieUseCase

class FavoriteViewModel(
    private val movieUseCase: MovieUseCase
):ViewModel() {

    private val _favorites: MutableLiveData<List<Movie>> = MutableLiveData()
    val getAllFavorite: LiveData<List<Movie>> = _favorites

    init {
        getAllFavorite()
    }

    private fun getAllFavorite() {
        movieUseCase.favorites().asLiveData().observeForever {
            _favorites.postValue(it)
        }
    }



}