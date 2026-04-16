# 模块说明：domain

## 职责
纯业务契约层：
1. 领域模型（例如 `MineProfile`）。
2. 仓库接口（例如 `MineRepository`）。
3. UseCase（例如 `SendLoginSmsCodeUseCase`、`LoginAndFetchMineProfileUseCase`、`GetLocalMineProfileUseCase`、`LogoutUseCase`）。

## 规则
1. 不依赖 `data` 和 `feature`。
2. 不出现 Retrofit、Room、Android Framework 细节。
