# compose-project-data

## 范围
- `data` 与 `domain` 的接口和实现。

## 规范
1. `domain` 仅定义仓库接口与 UseCase。
2. `data` 负责 remote/local/repository/mapper。
3. 仓库实现需要有清晰中文 KDoc，说明数据优先级。
4. DTO 与 Domain Model 映射必须集中在 `mapper`。

## 依赖方向
- `data -> domain`
- `domain` 不能反向依赖 `data`

## 文档同步要求
1. 仓库接口变化：同步更新 `docs/modules/domain.md`。
2. 数据实现或映射变化：同步更新 `docs/modules/data.md`。
