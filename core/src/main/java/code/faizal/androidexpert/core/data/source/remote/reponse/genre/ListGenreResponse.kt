package code.faizal.androidexpert.core.data.source.remote.reponse.genre

import com.google.gson.annotations.SerializedName

data class ListGenreResponse(

    @SerializedName("genres")
    val genresResponse: List<GenreResponse>
)
