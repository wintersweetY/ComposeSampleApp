# compose-project-layout

## 范围
- 底部导航、路由定义、页面 Contract、页面状态渲染。

## 规范
1. 页面使用 `UiState / UiIntent / UiEffect`。
2. 路由定义放 `feature:*:api`。
3. 页面实现放 `feature:*:impl`。
4. `app/navigation` 仅做路由装配，不写业务。

## 代码注释要求
- 对外可见的 `Route`、`ViewModel`、`Contract` 必须有中文 KDoc。
- KDoc 要包含必要 `@param` 和 `@return`。

## 文档同步要求
1. 路由变化：同步更新 `docs/modules/app.md` 和相关 `docs/modules/feature-*.md`。
2. 页面契约变化：同步更新对应 feature 模块文档。
