package com.example.mygallery.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygallery.common.Resource
import com.example.mygallery.presentation.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T, S> Flow<Resource<List<T>>>.collectRequests(
        state: MutableStateFlow<UIState<S>>,
        mappedData: (List<T>) -> S,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectRequests.collect {
                when (it) {
                    is Resource.Loading -> {
                        state.value = UIState.Loading()
                    }

                    is Resource.Error -> it.massage?.let { error ->
                        state.value = UIState.Error(error)
                    }

                    is Resource.Success -> it.data?.let { data ->
                        state.value = UIState.Success(mappedData(data))
                    }
                }
            }
        }
    }

    protected fun <T, S> Flow<Resource<T>>.collectRequest(
        state: MutableStateFlow<UIState<S>>,
        mappedData: (T) -> S,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectRequest.collect {
                when (it) {
                    is Resource.Loading -> {
                        state.value = UIState.Loading()
                    }
                    is Resource.Error -> it.massage?.let { error ->
                        state.value = UIState.Error(error)
                    }
                    is Resource.Success -> it.data?.let { data ->
                        state.value = UIState.Success(mappedData(data))
                    }
                }
            }
        }
    }
}