package com.ashehata.orange_task.modules.home

import com.ashehata.orange_task.base.BaseEvent
import com.ashehata.orange_task.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<BaseEvent, HomeViewState, HomeState>() {


    init {
        launchCoroutine {

        }
    }

    override fun handleEvents(event: BaseEvent) {

    }

    override fun createInitialViewState(): HomeViewState {
        return HomeViewState()
    }

}
