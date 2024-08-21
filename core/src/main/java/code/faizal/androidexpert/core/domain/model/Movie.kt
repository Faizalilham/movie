package code.faizal.androidexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val posterPath : String,
    val isAdult : Boolean,
    val overview : String,
    val releaseDate : String,
    val id : String,
    val originalTitle : String,
    val originalLanguage : String,
    val movieTitle : String,
    val backdropPath : String,
    val popularity : Float,
    val voteCount : Int,
    val isVideo : Boolean,
    val averageRating : Float,
    var isFavorite : Boolean,
): Parcelable
