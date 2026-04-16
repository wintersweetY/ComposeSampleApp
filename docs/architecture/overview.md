# 架构总览

## 1. 目标
本项目采用 **多模块 + Clean Architecture + MVI(UDF) + Hilt + Compose**。

核心目标：
1. 以 feature 为单位隔离业务改动。
2. 保持依赖单向流动，控制耦合。
3. 统一依赖注入方式，提升可维护性。

## 2. 模块清单
```text
:app
:core:common
:core:ui
:core:designsystem
:core:network
:core:database
:domain
:data
:feature:home:api
:feature:home:impl
:feature:square:api
:feature:square:impl
:feature:mine:api
:feature:mine:impl
```

## 3. 依赖规则
1. `app` 负责装配，可依赖各 feature 模块。
2. `feature:*:impl` 仅依赖 `feature:*:api`、`domain`、`core:*`。
3. `feature:*:api` 不得依赖 `impl`。
4. `data` 依赖 `domain` 并实现仓库接口。
5. `domain` 不得依赖 `data` 或 `feature`。

## 4. UI 模式
1. 页面契约统一使用 `UiState / UiIntent / UiEffect`。
2. ViewModel 通过 `StateFlow` 暴露状态。
3. Compose 层只负责渲染与事件上抛。
4. 导航由 `app/navigation` 统一组装。
5. 我的页当前采用真实调用链路：`发送短信验证码 -> 手机号验证码登录 -> 查询用户详情`。

## 5. DI 模式
1. 全量使用 Hilt。
2. `Application` 使用 `@HiltAndroidApp`。
3. `Activity` 使用 `@AndroidEntryPoint`。
4. `ViewModel` 使用 `@HiltViewModel`。
5. Hilt Module 按职责放在所属模块中。
6. 运行时环境配置由 `app` 提供，`data` 通过抽象接口读取。

## 6. 环境与构建
1. 使用 buildType 维护两套环境：`debug(dev)`、`release(生产)`。
2. `debug` 与 `release` 分别配置独立的 `BASE_URL` 与 `CHANNEL`。
3. 生产发布使用 `release` 变体。

## 7. 网络分层
1. `core:network` 提供网络基础能力（`NetworkClient`、标准响应结构）。
2. `core:common` 定义跨模块配置抽象（`ApiRuntimeConfig`）。
3. `app` 提供配置实现（`baseUrl/channel/deviceId` 等）。
4. `data` 负责接口定义、DTO、remote datasource、repository 聚合。
5. `domain` 只暴露业务模型与 use case，不感知 Retrofit。

## 8. 子模块文档
- `docs/modules/app.md`
- `docs/modules/core-common.md`
- `docs/modules/core-ui.md`
- `docs/modules/core-designsystem.md`
- `docs/modules/core-network.md`
- `docs/modules/core-database.md`
- `docs/modules/domain.md`
- `docs/modules/data.md`
- `docs/modules/feature-home-api.md`
- `docs/modules/feature-home-impl.md`
- `docs/modules/feature-square-api.md`
- `docs/modules/feature-square-impl.md`
- `docs/modules/feature-mine-api.md`
- `docs/modules/feature-mine-impl.md`
