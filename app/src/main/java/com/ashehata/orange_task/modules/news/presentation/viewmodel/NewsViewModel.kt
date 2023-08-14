package com.ashehata.orange_task.modules.news.presentation.viewmodel

import com.ashehata.orange_task.base.BaseViewModel
import com.ashehata.orange_task.modules.news.domain.usecase.GetNewsUseCase
import com.ashehata.orange_task.modules.news.presentation.contract.NewsEvent
import com.ashehata.orange_task.modules.news.presentation.contract.NewsState
import com.ashehata.orange_task.modules.news.presentation.contract.NewsViewState
import com.ashehata.orange_task.modules.news.presentation.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel<NewsEvent, NewsViewState, NewsState>() {

    /**
     * For search feature
     */
    private val searchDelay = 500L
    private var searchJob: Job? = null

    init {
        getAllNews()
    }

    private fun getAllNews(keyword: String = "apple") {
        launchCoroutine {
            setLoading()
            val news = getNewsUseCase.execute(keyword = keyword)
            viewStates?.allNews?.clear()
            val list = news.map { it.toUIModel() }
            setDoneLoading()
            viewStates?.allNews?.addAll(list)
        }
    }

    override fun handleEvents(event: NewsEvent) {
        when (event) {
            is NewsEvent.OnArticleClicked -> {
                setState {
                    NewsState.OpenArticleDetailsScreen(event.news)
                }
            }

            NewsEvent.OnSettingClicked -> {
                setState {
                    NewsState.OpenSettingScreen
                }
            }

            NewsEvent.RefreshScreen -> {
                val searchKeyword = viewStates?.searchText?.value?.trim()
                if (searchKeyword.isNullOrEmpty())
                    getAllNews()
                else
                    getAllNews(searchKeyword)
            }

            is NewsEvent.OnSearch -> {
                searchJob?.cancel()
                val searchKeyword = event.keyword.trim()
                if (searchKeyword.isEmpty()) {
                    getAllNews()
                } else {
                    searchJob = launchCoroutine {
                        delay(searchDelay)
                        getAllNews(keyword = searchKeyword)
                    }
                }
            }
        }
    }

    override fun createInitialViewState(): NewsViewState {
        return NewsViewState()
    }
}