package com.foresightx.composesampleapp.data.mapper

import com.foresightx.composesampleapp.data.model.TabContentDto
import com.foresightx.composesampleapp.domain.model.TabContent

/**
 * 将 DTO 转换为领域模型。
 *
 * @receiver 数据层 DTO。
 * @return 领域层模型。
 */
fun TabContentDto.toDomain(): TabContent = TabContent(
    title = title,
    subtitle = subtitle,
)
