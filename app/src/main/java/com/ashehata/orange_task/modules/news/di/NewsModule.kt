package com.ashehata.orange_task.modules.news.di

import com.ashehata.orange_task.modules.news.data.remote.NewsRemoteDataSource
import com.ashehata.orange_task.modules.news.data.remote.NewsRemoteDataSourceImpl
import com.ashehata.orange_task.modules.news.data.repository.NewsRepositoryImpl
import com.ashehata.orange_task.modules.news.data.retrofit.service.NewsService
import com.ashehata.orange_task.modules.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsModule {

    @ViewModelScoped
    @Binds
    abstract fun bindArticlesRemoteDataSource(articlesRemoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource


    @ViewModelScoped
    @Binds
    abstract fun bindArticlesRepository(articlesRepoImpl: NewsRepositoryImpl): NewsRepository


    companion object {
        @ViewModelScoped
        @Provides
        fun provideArticlesService(retrofit: Retrofit): NewsService =
            retrofit.create(NewsService::class.java)

    }
}