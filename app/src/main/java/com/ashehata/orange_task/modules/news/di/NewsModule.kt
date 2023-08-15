package com.ashehata.orange_task.modules.news.di

import com.ashehata.orange_task.database.room.AppDatabase
import com.ashehata.orange_task.modules.news.data.local.dao.NewsDao
import com.ashehata.orange_task.modules.news.data.local.source.NewsLocalDataSource
import com.ashehata.orange_task.modules.news.data.local.source.NewsLocalDataSourceImpl
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
    abstract fun bindNewsRemoteDataSource(newsRemoteDataSourceImpl: NewsRemoteDataSourceImpl): NewsRemoteDataSource


    @ViewModelScoped
    @Binds
    abstract fun bindNewsLocalDataSource(newsLocalDataSourceImpl: NewsLocalDataSourceImpl): NewsLocalDataSource

    @ViewModelScoped
    @Binds
    abstract fun bindNewsRepository(newsRepoImpl: NewsRepositoryImpl): NewsRepository


    companion object {
        @ViewModelScoped
        @Provides
        fun provideNewsService(retrofit: Retrofit): NewsService =
            retrofit.create(NewsService::class.java)


        @ViewModelScoped
        @Provides
        fun provideNewsDao(appDatabase: AppDatabase): NewsDao = appDatabase.newsDao()

    }
}