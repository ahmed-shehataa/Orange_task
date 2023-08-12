package com.ashehata.orange_task.modules.news.presentation.viewmodel

import com.ashehata.orange_task.base.BaseViewModel
import com.ashehata.orange_task.modules.news.domain.usecase.GetNewsUseCase
import com.ashehata.orange_task.modules.news.presentation.contract.NewsEvent
import com.ashehata.orange_task.modules.news.presentation.contract.NewsState
import com.ashehata.orange_task.modules.news.presentation.contract.NewsViewState
import com.ashehata.orange_task.modules.news.presentation.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel<NewsEvent, NewsViewState, NewsState>() {


    init {
        getAllNews()
    }

    private fun getAllNews() {
        launchCoroutine {
            val news = getNewsUseCase.execute()
            viewStates?.allNews?.clear()
            val list = news.map { it.toUIModel() }
            viewStates?.allNews?.addAll(list)
        }
    }

    override fun handleEvents(event: NewsEvent) {
        when (event) {
            is NewsEvent.OnArticleClicked -> {
                setState {
                    NewsState.OpenArticleDetailsScreen(event.article)
                }
            }

            NewsEvent.OnSettingClicked -> {
                setState {
                    NewsState.OpenSettingScreen
                }
            }

            NewsEvent.RefreshScreen -> {
                getAllNews()
            }
        }
    }

    override fun createInitialViewState(): NewsViewState {
        return NewsViewState()
    }
}