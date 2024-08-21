package code.faizal.androidexpert.core.data.source.remote.reponse

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("poster_path")
    val newPathPoster : String,

    @field:SerializedName("adult")
    val isAdult : Boolean,

    @field:SerializedName("overview")
    val movieOverview : String,

    @field:SerializedName("release_date")
    val releaseDate : String,

    @field:SerializedName("genre_ids")
    val genreIds : List<Int>,

    @field:SerializedName("id")
    val movieId : String,

    @field:SerializedName("original_title")
    val originalTitle : String,

    @field:SerializedName("original_language")
    val originalLanguage : String,

    @field:SerializedName("title")
    val movieTitle : String,

    @field:SerializedName("backdrop_path")
    val backdropPath : String,

    @field:SerializedName("popularity")
    val popularity : Float,

    @field:SerializedName("vote_count")
    val voteCount : Int,

    @field:SerializedName("video")
    val isVideo : Boolean,

    @field:SerializedName("vote_average")
    val averageRating : Float
)
