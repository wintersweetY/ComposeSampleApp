# compose-project-data

## 范围
- `data` 与 `domain` 的接口和实现。

## 规范
1. `domain` 仅定义仓库接口与 UseCase。
2. `data` 负责 remote/local/repository/mapper。
3. 仓库实现需要有清晰中文 KDoc，说明数据优先级。
4. DTO 与 Domain Model 映射必须集中在 `mapper`。
5. 环境配置（baseUrl、渠道、设备标识）不得硬编码在 `data/build.gradle.kts`，必须通过抽象接口注入。
6. 多环境统一在 `app` 的 buildType/flavor 管理，`data` 禁止感知变体细节。
7. 模板工程命名必须保持中立，禁止在类名、接口名、字段名中引入任何业务品牌词。

## 依赖方向
- `data -> domain`
- `domain` 不能反向依赖 `data`
- 环境值来源：`app -> core-common(接口) -> data(消费接口)`

## 文档同步要求
1. 仓库接口变化：同步更新 `docs/modules/domain.md`。
2. 数据实现或映射变化：同步更新 `docs/modules/data.md`。
3. 网络接口定义或响应结构变化：同步更新 `docs/modules/core-network.md` 与 `docs/modules/feature-*.md` 对应页面文档。
4. 登录态策略变化（本地持久化字段、退出登录链路、token 读写来源）必须同步更新 `docs/modules/data.md` 与 `docs/modules/feature-mine-impl.md`。
