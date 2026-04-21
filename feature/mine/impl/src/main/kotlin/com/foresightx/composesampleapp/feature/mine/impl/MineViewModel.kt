package com.foresightx.composesampleapp.feature.mine.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foresightx.composesampleapp.domain.usecase.GetLocalMineProfileUseCase
import com.foresightx.composesampleapp.domain.usecase.LoginAndFetchMineProfileUseCase
import com.foresightx.composesampleapp.domain.usecase.LogoutUseCase
import com.foresightx.composesampleapp.domain.usecase.SendLoginSmsCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 我的页 ViewModel。
 *
 * @param loginAndFetchMineProfileUseCase 登录并查询我的页信息用例。
 */
@HiltViewModel
class MineViewModel @Inject constructor(
    private val loginAndFetchMineProfileUseCase: LoginAndFetchMineProfileUseCase,
    private val sendLoginSmsCodeUseCase: SendLoginSmsCodeUseCase,
    private val getLocalMineProfileUseCase: GetLocalMineProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MineUiState())
    val uiState: StateFlow<MineUiState> = _uiState.asStateFlow()

    init {
        loadLocalSession()
    }

    /**
     * 处理用户意图。
     *
     * @param intent 用户意图。
     */
    fun onIntent(intent: MineUiIntent) {
        when (intent) {
            is MineUiIntent.ChangePhone -> _uiState.update {
                it.copy(phone = intent.phone, statusMessage = null, errorMessage = null)
            }

            is MineUiIntent.ChangeCode -> _uiState.update {
                it.copy(code = intent.code, statusMessage = null, errorMessage = null)
            }

            MineUiIntent.SendSmsCode -> sendSmsCode()
            MineUiIntent.SubmitLogin -> submitLogin()
            MineUiIntent.Logout -> logout()
        }
    }

    private fun loadLocalSession() {
        viewModelScope.launch {
            val profile = getLocalMineProfileUseCase()
            if (profile != null) {
                _uiState.update {
                    it.copy(
                        nickName = profile.nickName.ifBlank { "未命名用户" },
                        avatarUrl = profile.avatar,
                        userId = profile.userId,
                        isLoggedIn = true,
                        statusMessage = "已恢复本地登录状态",
                        errorMessage = null,
                    )
                }
            }
        }
    }

    private fun sendSmsCode() {
        val current = _uiState.value
        if (current.phone.isBlank()) {
            _uiState.update { it.copy(errorMessage = "请输入手机号") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isSendingCode = true, statusMessage = null, errorMessage = null) }
            runCatching {
                sendLoginSmsCodeUseCase(current.phone)
            }.onSuccess { success ->
                _uiState.update {
                    it.copy(
                        isSendingCode = false,
                        statusMessage = if (success) "验证码已发送，请注意查收" else null,
                        errorMessage = if (success) null else "验证码发送失败",
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isSendingCode = false,
                        statusMessage = null,
                        errorMessage = throwable.message ?: "验证码发送失败，请稍后重试",
                    )
                }
            }
        }
    }

    private fun submitLogin() {
        val current = _uiState.value
        if (current.phone.isBlank() || current.code.isBlank()) {
            _uiState.update { it.copy(errorMessage = "请输入手机号和验证码") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, statusMessage = null, errorMessage = null) }
            runCatching {
                loginAndFetchMineProfileUseCase(current.phone, current.code)
            }.onSuccess { profile ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        nickName = profile.nickName.ifBlank { "未命名用户" },
                        avatarUrl = profile.avatar,
                        userId = profile.userId,
                        isLoggedIn = true,
                        statusMessage = "登录成功",
                        errorMessage = null,
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = null,
                        errorMessage = throwable.message ?: "请求失败，请检查网络与参数",
                    )
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, statusMessage = null, errorMessage = null) }
            runCatching {
                logoutUseCase()
            }.onSuccess {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        nickName = "未登录",
                        avatarUrl = null,
                        userId = null,
                        code = "",
                        isLoggedIn = false,
                        statusMessage = "已退出登录",
                        errorMessage = null,
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        statusMessage = null,
                        errorMessage = throwable.message ?: "退出失败，请稍后重试",
                    )
                }
            }
        }
    }
}
