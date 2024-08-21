package code.faizal.androidexpert.core.data.source.remote.reponse

import com.google.gson.annotations.SerializedName

data class ListMovieResponse<T>(

    @field:SerializedName("page")
    val page : Int,

    @field:SerializedName("results")
    val results : List<T>,

    @field:SerializedName("total_results")
    val totalResults : Int,

    @field:SerializedName("pages")
    val totalPages : Int

)
