# 模块说明：feature-mine-impl

## 职责
我的页实现层。

## 当前功能
1. 按 Figma「我的-普通用户」节点实现页面结构（顶部用户信息、会员卡、能量区、萌宠档案、报告记录、功能列表）。
2. 支持点击“获取验证码”调用短信接口。
3. 点击登录后拉取用户详情并刷新页面昵称/登录态。
4. 登录成功后本地保存登录态，下次进入自动恢复。
5. 支持点击“退出”调用服务端接口并清空本地登录态。
6. 页面底部保留状态提示与错误提示，用于联调登录链路。

## 依赖
`feature-mine-api`、`domain`、`core:*`

## 状态流
`MineUiIntent -> MineViewModel -> (SendLoginSmsCodeUseCase / LoginAndFetchMineProfileUseCase / GetLocalMineProfileUseCase / LogoutUseCase) -> MineUiState`
