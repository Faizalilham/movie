@file:Suppress("SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection")

package code.faizal.androidexpert.di


import code.faizal.androidexpert.core.domain.usecase.MovieUseCase
import code.faizal.androidexpert.core.domain.usecase.impl.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {


    @Binds
    @Singleton
    abstract fun provideMovieMRepository(movieInteractor : MovieUseCaseImpl): MovieUseCase
}