package com.ashehata.orange_task.modules.articles.presentation.viewmodel

import com.ashehata.orange_task.base.BaseViewModel
import com.ashehata.orange_task.modules.articles.domain.usecase.GetArticlesUseCase
import com.ashehata.orange_task.modules.articles.presentation.contract.ArticlesEvent
import com.ashehata.orange_task.modules.articles.presentation.contract.ArticlesState
import com.ashehata.orange_task.modules.articles.presentation.contract.ArticlesViewState
import com.ashehata.orange_task.modules.articles.presentation.mapper.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : BaseViewModel<ArticlesEvent, ArticlesViewState, ArticlesState>() {


    init {
        getAllArticles()
    }

    private fun getAllArticles() {
        launchCoroutine {
            val articles = getArticlesUseCase.execute()
            viewStates?.allArticles?.clear()
            val list = articles.map { it.toUIModel() }
            viewStates?.allArticles?.addAll(list)
        }
    }

    override fun handleEvents(event: ArticlesEvent) {
        when (event) {
            is ArticlesEvent.OnArticleClicked -> {
                setState {
                    ArticlesState.OpenArticleDetailsScreen(event.article)
                }
            }

            ArticlesEvent.OnSettingClicked -> {
                setState {
                    ArticlesState.OpenSettingScreen
                }
            }

            ArticlesEvent.RefreshScreen -> {
                getAllArticles()
            }
        }
    }

    override fun createInitialViewState(): ArticlesViewState {
        return ArticlesViewState()
    }
}