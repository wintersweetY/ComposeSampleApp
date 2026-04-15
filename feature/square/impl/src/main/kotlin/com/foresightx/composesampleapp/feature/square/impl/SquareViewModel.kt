package com.foresightx.composesampleapp.feature.square.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foresightx.composesampleapp.domain.usecase.GetSquareTabContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 广场页 ViewModel。
 *
 * @param getSquareTabContentUseCase 广场页数据用例。
 */
@HiltViewModel
class SquareViewModel @Inject constructor(
    private val getSquareTabContentUseCase: GetSquareTabContentUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SquareUiState())
    val uiState: StateFlow<SquareUiState> = _uiState.asStateFlow()

    init {
        onIntent(SquareUiIntent.Load)
    }

    /**
     * 处理用户意图。
     *
     * @param intent 用户意图。
     */
    fun onIntent(intent: SquareUiIntent) {
        when (intent) {
            SquareUiIntent.Load -> loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val content = getSquareTabContentUseCase()
            _uiState.update { it.copy(title = content.title, subtitle = content.subtitle) }
        }
    }
}
