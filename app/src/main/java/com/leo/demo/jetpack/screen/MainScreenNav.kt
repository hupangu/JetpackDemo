package com.leo.demo.jetpack.screen

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leo.demo.jetpack.R
import com.leo.demo.jetpack.category.CategoryScreen
import com.leo.demo.jetpack.home.HomeScreen
import com.leo.demo.jetpack.me.MeScreen
import com.leo.demo.jetpack.shopping.ShoppingScreen

sealed class MainScreenNav(val route: String, @StringRes val resourceId: Int) {
    companion object {
        val items = listOf(
            Home,
            Category,
            Shopping,
            Me
        )

        @Composable
        fun loadNavHost(controller: NavHostController, modifier: Modifier) {
            NavHost(
                navController = controller,
                startDestination = Home.route,
                modifier = modifier
            ) {
                composable(Home.route) { HomeScreen(controller) }
                composable(Category.route) { CategoryScreen(controller) }
                composable(Shopping.route) { ShoppingScreen(controller) }
                composable(Me.route) { MeScreen(controller) }
            }
        }
    }

    object Home : MainScreenNav("home", R.string.main_nav_home)
    object Category : MainScreenNav("category", R.string.main_nav_category)
    object Shopping : MainScreenNav("shopping", R.string.main_nav_shopping)
    object Me : MainScreenNav("me", R.string.main_nav_me)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                MainScreenNav.items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any {
                            Log.d(
                                "MainScreen",
                                "MainScreen: it.route=${it.route}, screen.route=${screen.route}"
                            )
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        MainScreenNav.loadNavHost(
            controller = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

