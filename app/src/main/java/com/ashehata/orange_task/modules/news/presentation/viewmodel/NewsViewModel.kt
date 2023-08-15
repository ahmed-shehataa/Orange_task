package com.ashehata.orange_task.modules.news.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ashehata.orange_task.base.BaseViewModel
import com.ashehata.orange_task.modules.news.presentation.contract.NewsEvent
import com.ashehata.orange_task.modules.news.presentation.contract.NewsState
import com.ashehata.orange_task.modules.news.presentation.contract.NewsViewState
import com.ashehata.orange_task.modules.news.presentation.source.NewsPagingFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsPagingFlow: NewsPagingFlow
) : BaseViewModel<NewsEvent, NewsViewState, NewsState>() {

    private val searchDelay = 500L
    private var searchJob: Job? = null
    private val firstLoadKeyword = "apple"

    init {
        getAllNews()
    }

    private fun getAllNews(keyword: String = firstLoadKeyword) {
        launchCoroutine {
            viewStates?.newsFlow?.emit(newsPagingFlow.getNewsFlow(keyword).cachedIn(viewModelScope).firstOrNull())
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