package code.faizal.androidexpert.core.di

import android.content.Context
import code.faizal.androidexpert.core.data.source.local.room.MovieDao
import code.faizal.androidexpert.core.data.source.local.room.MovieDatabase
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Provides
    @Singleton
    fun roomDatabase(@ApplicationContext context : Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "db_room_movie")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun movieDao(movieDatabase : MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

}