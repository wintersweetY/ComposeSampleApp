# 模块说明：data

## 职责
数据实现层：
1. remote/local 数据源。
2. DTO 与 Mapper。
3. Repository 实现。
4. Hilt 提供与绑定模块。

## 当前网络接入
1. `remote/api/AuthApiService`：定义发送验证码、登录与用户详情接口。
2. `remote/AuthRemoteDataSource`：封装接口调用与响应解包。
3. `remote/interceptor/ApiHeadersInterceptor`：统一注入 `os/channel/deviceId/appVersion` 等基础请求头。
4. `repository/MineRepositoryImpl`：聚合“发验证码 -> 登录 -> 查详情”流程。

## 配置来源
1. `data` 不再维护环境 `BuildConfig` 字段。
2. 网络相关运行时参数通过 `ApiRuntimeConfig` 注入。
3. 具体值由 `app` 层提供（符合入口层管理环境的实践）。

## 规则
1. 实现 `domain` 定义的仓库接口。
2. DTO 转 domain 必须在 `mapper` 统一处理。
3. Hilt Module 仅做对象装配，不在模块里写业务逻辑。
