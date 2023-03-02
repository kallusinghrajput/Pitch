package com.pitch.ui.activities.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pitch.api.apiinterface.APIS
import com.pitch.api.apistate.Result
import com.pitch.data.model.Test
import com.pitch.data.repository.HomeRepository
import com.pitch.utils.timber.logE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _channelFlow = MutableSharedFlow<Boolean>()
    val channelFlow = _channelFlow.asSharedFlow()


    private val _result = Channel<Result<List<Test>?>>()
    val result = _result.receiveAsFlow()


    init {
        logE(" lotating")

        viewModelScope.launch {

            homeRepository.fetchPost().collectLatest {
                _result.send(it)
            }
        }
    }

    fun bottomProgressBar(isVisible: Boolean) {
        viewModelScope.launch {
            _channelFlow.emit(isVisible)
        }
    }
}