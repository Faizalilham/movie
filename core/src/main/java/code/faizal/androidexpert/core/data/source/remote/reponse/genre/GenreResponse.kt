package code.faizal.androidexpert.core.data.source.remote.reponse.genre

import com.google.gson.annotations.SerializedName

data class GenreResponse(

    @SerializedName("id")
    val id : Int,

    @SerializedName("name")
    val name : String
)
