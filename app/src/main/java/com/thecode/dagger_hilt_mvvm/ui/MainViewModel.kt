package com.thecode.dagger_hilt_mvvm.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.repository.MainRepository
import com.thecode.dagger_hilt_mvvm.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
    @ViewModelInject
    constructor(
        private val mainRepository: MainRepository,
        @Assisted private val savedStateHandle: SavedStateHandle
    ): ViewModel(){
        private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

        val dataState: LiveData<DataState<List<Blog>>>
            get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvents ->{
                    mainRepository.getBlog()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None ->{
                    // No action
                }
            }
        }
    }
}


sealed class MainStateEvent{
    object GetBlogEvents: MainStateEvent()
    object None: MainStateEvent()
}

