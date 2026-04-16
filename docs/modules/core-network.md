# 模块说明：core-network

## 职责
提供网络基础设施，不承载业务接口。

## 当前内容
1. `NetworkClient`：创建 `OkHttpClient` 与 `Retrofit`，支持注入拦截器列表。
2. `ApiResultDto<T>`：统一接口响应结构（`data/code/message`）并提供成功码校验。

## 约束
1. 本模块不放业务 `ApiService`。
2. 本模块不依赖 `domain` 与 `feature`。
3. 业务接口放在 `data/remote/api`，由 `data/di` 负责注入。
4. Retrofit 接口路径统一使用相对路径（如 `v1/xxx`），禁止以 `/` 开头，避免丢失 baseUrl 的子路径前缀。
