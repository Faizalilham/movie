package code.faizal.androidexpert.core.data.source.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @ColumnInfo(name = "poster_path")
    val newPathPoster : String,

    @ColumnInfo(name = "adult")
    val isAdult : Boolean,

    @ColumnInfo(name = "overview")
    val movieOverview : String,

    @ColumnInfo(name = "release_date")
    val releaseDate : String,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val movieId : String,

    @ColumnInfo(name = "original_tittle")
    val originalTitle : String,

    @ColumnInfo(name ="original_language")
    val originalLanguage : String,

    @ColumnInfo(name = "tittle")
    val movieTitle : String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath : String,

    @ColumnInfo(name = "popularity")
    val popularity : Float,

    @ColumnInfo(name = "vote_count")
    val voteCount : Int,

    @ColumnInfo(name = "vote_video")
    val isVideo : Boolean,

    @ColumnInfo(name = "average")
    val averageRating : Float,

    @ColumnInfo(name = "isFavorite")
    var isFavorite : Boolean = false
)
