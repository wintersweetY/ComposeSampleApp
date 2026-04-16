# 模块说明：app

## 职责
1. Application 与 Activity 入口。
2. 全局导航组装。
3. 运行时环境配置提供者（Hilt Module）。
4. 仅做装配，不实现业务逻辑。

## 环境配置（buildType）
当前支持两套环境：
1. `debug`：开发环境（dev）。
2. `release`：生产环境。

## 当前配置职责
1. 提供 `ApiRuntimeConfig` 实现。
2. 通过 buildType 的 `BuildConfig` 提供 `BASE_URL` 与 `CHANNEL`。
3. 在运行时生成 `deviceId`（`Settings.Secure.ANDROID_ID`）。

## 关键路径
- `app/src/main/java/com/foresightx/composesampleapp/ComposeSampleApplication.kt`
- `app/src/main/java/com/foresightx/composesampleapp/MainActivity.kt`
- `app/src/main/java/com/foresightx/composesampleapp/navigation/`
- `app/src/main/java/com/foresightx/composesampleapp/di/AppConfigModule.kt`
