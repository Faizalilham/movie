package code.faizal.androidexpert.favorite.di

import android.content.Context
import code.faizal.androidexpert.di.FavoriteModuleDependencies
import code.faizal.androidexpert.favorite.presentation.screen.FavoriteActivity
import dagger.BindsInstance
import dagger.Component


@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    // Inisiasi di activity mana inject akan dilakukan
    fun inject(activity : FavoriteActivity)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}