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

## 5. DI 模式
1. 全量使用 Hilt。
2. `Application` 使用 `@HiltAndroidApp`。
3. `Activity` 使用 `@AndroidEntryPoint`。
4. `ViewModel` 使用 `@HiltViewModel`。
5. Hilt Module 放在职责归属模块中。

## 6. 子模块文档
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
