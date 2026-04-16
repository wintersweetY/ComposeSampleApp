# compose-project-hilt

## 范围
- Hilt Application、Activity、ViewModel、Module。

## 规范
1. `Application` 必须 `@HiltAndroidApp`。
2. `Activity` 必须 `@AndroidEntryPoint`。
3. `ViewModel` 必须 `@HiltViewModel` + `@Inject constructor`。
4. Hilt Module 按职责放在所属模块（例如 `data/di`）。
5. 禁止回退到手写 `Service Locator`。
6. 网络对象装配（`OkHttpClient`、`Retrofit`、`ApiService`）应放在业务所属模块（当前为 `data/di`）。
7. 环境配置值由 `app` 提供，`data` 通过接口注入使用。

## 构建要求
- 统一使用稳定 AGP/Gradle/Kotlin/Hilt 版本组合。
- 变更版本后必须执行 `:app:assembleDebug` 验证。

## 文档同步要求
1. 注入边界或模块位置变化：同步更新 `docs/architecture/overview.md`。
2. Hilt 配置流程变化：同步更新 `docs/skills-guide.md` 与相关模块文档。
