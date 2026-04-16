# 模块说明：core-common

## 职责
提供全局通用基础能力：
1. `AppResult`
2. `AppConstants`
3. `AppDispatchers`
4. 跨模块配置抽象：`ApiRuntimeConfig`

## 规则
1. 仅放“跨模块可复用”的抽象与工具。
2. 不依赖 Android UI 组件。
3. 不放业务实现，只放接口或通用能力。
