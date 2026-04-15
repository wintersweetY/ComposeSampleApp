# Compose Project Skill（项目级）

## 作用
这是本仓库的项目级总技能，用于统一协调多个子技能，保证多模块架构一致性。

## 子技能
1. `compose-project-layout`：页面布局、导航、UI 契约。
2. `compose-project-data`：data/domain 分层、仓库与用例。
3. `compose-project-hilt`：Hilt 依赖注入与模块绑定。

## 先读文档
1. 架构总览：`docs/architecture/overview.md`
2. 模块文档：`docs/modules/`
3. 技能说明：`docs/skills-guide.md`

## 使用规则
1. 涉及页面改动时：必须先看 `layout`。
2. 涉及数据流改动时：必须同时看 `data`。
3. 涉及依赖装配改动时：必须看 `hilt`。
4. 每次结构性改动后，需要同步更新相关 Skill 文档。
5. 每次改动如果影响架构、模块职责、依赖关系、开发流程，必须同步更新 `docs/` 下对应文档。

## 文档同步规则
1. 全局架构变化：更新 `docs/architecture/overview.md`。
2. 模块职责或边界变化：更新 `docs/modules/<module>.md`。
3. Skill 使用方式变化：更新 `docs/skills-guide.md`。
4. 未更新文档不得视为完成。

## 架构红线
1. `app` 只做入口和导航组装。
2. `feature:*:impl` 不得直接依赖 `data`。
3. `domain` 不得依赖 `data` 或 `feature`。
4. Hilt Module 按职责放在所属模块，不堆到 `app/di`。
