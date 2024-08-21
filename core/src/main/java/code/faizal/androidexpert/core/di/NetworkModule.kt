package code.faizal.androidexpert.core.di

import code.faizal.androidexpert.core.data.source.RequestInterceptor
import code.faizal.androidexpert.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRequestInterceptor(): RequestInterceptor = RequestInterceptor()


    @Provides
    @Singleton
    fun httpLogging():HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    }



    @Provides
    @Singleton
    fun okHttpClient(requestInterceptor: RequestInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(150,TimeUnit.SECONDS)
            .readTimeout(150,TimeUnit.SECONDS)
            .build()
    }



    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun apiService(retrofit : Retrofit): ApiService {
       return  retrofit.create(ApiService::class.java)
    }
}