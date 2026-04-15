package com.foresightx.composesampleapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.foresightx.composesampleapp.feature.home.api.HomeRouteSpec
import com.foresightx.composesampleapp.feature.home.impl.HomeRoute
import com.foresightx.composesampleapp.feature.mine.api.MineRouteSpec
import com.foresightx.composesampleapp.feature.mine.impl.MineRoute
import com.foresightx.composesampleapp.feature.square.api.SquareRouteSpec
import com.foresightx.composesampleapp.feature.square.impl.SquareRoute

/**
 * 应用导航主机。
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { tab ->
                    val selected = currentDestination
                        ?.hierarchy
                        ?.any { it.route == tab.route } == true

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Text(tab.iconText) },
                        label = { Text(tab.label) },
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRouteSpec.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(HomeRouteSpec.route) { HomeRoute() }
            composable(SquareRouteSpec.route) { SquareRoute() }
            composable(MineRouteSpec.route) { MineRoute() }
        }
    }
}
