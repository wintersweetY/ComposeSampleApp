package com.foresightx.composesampleapp.feature.mine.impl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * 我的页路由入口。
 */
@Composable
fun MineRoute() {
    val vm: MineViewModel = hiltViewModel()
    val uiState by vm.uiState.collectAsState()
    MineScreen(uiState = uiState, onIntent = vm::onIntent)
}

/**
 * 我的页界面。
 *
 * @param uiState UI 状态。
 * @param onIntent 意图回调。
 */
@Composable
private fun MineScreen(
    uiState: MineUiState,
    onIntent: (MineUiIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = "我的", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = uiState.phone,
            onValueChange = { onIntent(MineUiIntent.ChangePhone(it)) },
            label = { Text("手机号") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = uiState.code,
            onValueChange = { onIntent(MineUiIntent.ChangeCode(it)) },
            label = { Text("验证码") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        Button(
            onClick = { onIntent(MineUiIntent.SendSmsCode) },
            enabled = !uiState.isSendingCode && uiState.phone.isNotBlank(),
        ) {
            Text(if (uiState.isSendingCode) "发送中..." else "获取验证码")
        }
        Button(
            onClick = { onIntent(MineUiIntent.SubmitLogin) },
            enabled = !uiState.isLoading,
        ) {
            Text("登录并加载我的信息")
        }
        Button(
            onClick = { onIntent(MineUiIntent.Logout) },
            enabled = !uiState.isLoading && uiState.isLoggedIn,
        ) {
            Text("退出登录")
        }
        if (uiState.isLoading) {
            CircularProgressIndicator()
        }
        Text(text = "昵称：${uiState.nickName}")
        Text(text = "用户ID：${uiState.userId ?: "-"}")
        uiState.statusMessage?.let { message ->
            Text(text = message, color = MaterialTheme.colorScheme.primary)
        }
        uiState.errorMessage?.let { error ->
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}

/**
 * 我的页预览（未登录态）。
 */
@Preview(showBackground = true, name = "Mine-LoggedOut")
@Composable
private fun MineScreenLoggedOutPreview() {
    MineScreen(
        uiState = MineUiState(
            phone = "13800138000",
            code = "123456",
            nickName = "未登录",
            userId = null,
            isLoggedIn = false,
            statusMessage = "请输入验证码后登录",
        ),
        onIntent = {},
    )
}

/**
 * 我的页预览（已登录态）。
 */
@Preview(showBackground = true, name = "Mine-LoggedIn")
@Composable
private fun MineScreenLoggedInPreview() {
    MineScreen(
        uiState = MineUiState(
            nickName = "示例用户",
            userId = 10001L,
            isLoggedIn = true,
            statusMessage = "已恢复本地登录状态",
        ),
        onIntent = {},
    )
}
