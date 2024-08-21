package code.faizal.androidexpert.core.data.source.local.room

import code.faizal.androidexpert.core.data.source.local.entity.MovieEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun movies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun favorites():Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies : List<MovieEntity>)

    @Update
    fun updateMovie(movieEntity : MovieEntity)

}