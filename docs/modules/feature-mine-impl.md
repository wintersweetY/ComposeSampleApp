# 模块说明：feature-mine-impl

## 职责
我的页实现层。

## 当前功能
1. 提供手机号、验证码输入。
2. 支持点击“获取验证码”调用短信接口。
3. 点击登录后拉取用户详情。
4. 登录成功后本地保存登录态，下次进入自动恢复。
5. 支持点击“退出登录”调用服务端接口并清空本地登录态。
6. 展示昵称、用户 ID、状态提示与错误信息。

## 依赖
`feature-mine-api`、`domain`、`core:*`

## 状态流
`MineUiIntent -> MineViewModel -> (SendLoginSmsCodeUseCase / LoginAndFetchMineProfileUseCase / GetLocalMineProfileUseCase / LogoutUseCase) -> MineUiState`
