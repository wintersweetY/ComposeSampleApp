package com.foresightx.composesampleapp.feature.home.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foresightx.composesampleapp.domain.usecase.GetHomeTabContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 首页 ViewModel。
 *
 * @param getHomeTabContentUseCase 首页数据用例。
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeTabContentUseCase: GetHomeTabContentUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<HomeUiEffect>()
    val uiEffect: SharedFlow<HomeUiEffect> = _uiEffect.asSharedFlow()

    init {
        onIntent(HomeUiIntent.Load)
    }

    /**
     * 处理用户意图。
     *
     * @param intent 用户触发的意图对象。
     */
    fun onIntent(intent: HomeUiIntent) {
        when (intent) {
            HomeUiIntent.Load -> loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val content = getHomeTabContentUseCase()
            _uiState.update { it.copy(title = content.title, subtitle = content.subtitle) }
        }
    }
}
