package com.example.postsapp.di

import com.example.alquran.common.Constants.BASE_URL
import com.example.postsapp.data.remote.PostsAPIService
import com.example.postsapp.data.repo.RepoImpl
import com.example.postsapp.domain.repo.Repo
import com.example.postsapp.domain.use_case.GetPostItemUseCase
import com.example.postsapp.domain.use_case.GetPostsUserCase
import com.example.postsapp.ui.main.detailscomponents.DetailsViewModel
import com.example.postsapp.ui.main.maincomponents.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostsApi():PostsAPIService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepo(api: PostsAPIService):Repo{
     return RepoImpl(api)
    }

    @Provides
    @Singleton
    fun providePostsUseCase(repo: Repo):GetPostsUserCase{
     return GetPostsUserCase(repo)
    }

    @Provides
    @Singleton
    fun providePostItemUseCase(repo: Repo):GetPostItemUseCase{
        return GetPostItemUseCase(repo)
    }


    @Provides
    @Singleton
    fun providesPostsViewModel(useCase: GetPostsUserCase): MainViewModel{
        return MainViewModel(useCase)
    }


    @Provides
    @Singleton
    fun providesPostDetailViewModel(useCase: GetPostItemUseCase): DetailsViewModel{
        return DetailsViewModel(useCase)
    }


}