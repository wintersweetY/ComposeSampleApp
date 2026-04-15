package com.foresightx.composesampleapp.feature.mine.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foresightx.composesampleapp.domain.usecase.GetMineTabContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 我的页 ViewModel。
 *
 * @param getMineTabContentUseCase 我的页数据用例。
 */
@HiltViewModel
class MineViewModel @Inject constructor(
    private val getMineTabContentUseCase: GetMineTabContentUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MineUiState())
    val uiState: StateFlow<MineUiState> = _uiState.asStateFlow()

    init {
        onIntent(MineUiIntent.Load)
    }

    /**
     * 处理用户意图。
     *
     * @param intent 用户意图。
     */
    fun onIntent(intent: MineUiIntent) {
        when (intent) {
            MineUiIntent.Load -> loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val content = getMineTabContentUseCase()
            _uiState.update { it.copy(title = content.title, subtitle = content.subtitle) }
        }
    }
}
