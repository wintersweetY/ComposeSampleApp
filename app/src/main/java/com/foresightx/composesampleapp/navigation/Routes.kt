package com.foresightx.composesampleapp.navigation

import com.foresightx.composesampleapp.feature.home.api.HomeRouteSpec
import com.foresightx.composesampleapp.feature.mine.api.MineRouteSpec
import com.foresightx.composesampleapp.feature.square.api.SquareRouteSpec

/**
 * 底部导航项。
 *
 * @param route 路由。
 * @param label 文案。
 * @param iconText 图标占位文本。
 */
data class BottomNavItem(
    val route: String,
    val label: String,
    val iconText: String,
)

/** 应用底部导航集合。 */
val bottomNavItems: List<BottomNavItem> = listOf(
    BottomNavItem(HomeRouteSpec.route, HomeRouteSpec.label, HomeRouteSpec.iconText),
    BottomNavItem(SquareRouteSpec.route, SquareRouteSpec.label, SquareRouteSpec.iconText),
    BottomNavItem(MineRouteSpec.route, MineRouteSpec.label, MineRouteSpec.iconText),
)
