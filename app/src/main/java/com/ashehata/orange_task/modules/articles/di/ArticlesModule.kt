package com.ashehata.orange_task.modules.articles.di

import com.ashehata.orange_task.modules.articles.data.remote.ArticlesRemoteDataSource
import com.ashehata.orange_task.modules.articles.data.remote.ArticlesRemoteDataSourceImpl
import com.ashehata.orange_task.modules.articles.data.repository.ArticlesRepositoryImpl
import com.ashehata.orange_task.modules.articles.data.retrofit.service.ArticlesService
import com.ashehata.orange_task.modules.articles.domain.repository.ArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class ArticlesModule {

    @ViewModelScoped
    @Binds
    abstract fun bindArticlesRemoteDataSource(articlesRemoteDataSourceImpl: ArticlesRemoteDataSourceImpl): ArticlesRemoteDataSource


    @ViewModelScoped
    @Binds
    abstract fun bindArticlesRepository(articlesRepoImpl: ArticlesRepositoryImpl): ArticlesRepository


    companion object {
        @ViewModelScoped
        @Provides
        fun provideArticlesService(retrofit: Retrofit): ArticlesService =
            retrofit.create(ArticlesService::class.java)

    }
}