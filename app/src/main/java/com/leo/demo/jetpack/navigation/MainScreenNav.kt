package com.leo.demo.jetpack.navigation

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leo.demo.jetpack.ui.R
import com.leo.demo.jetpack.ui.main.CATEGORY_SCREEN_ROUTE
import com.leo.demo.jetpack.ui.main.CategoryScreen
import com.leo.demo.jetpack.ui.main.HOME_SCREEN_ROUTE
import com.leo.demo.jetpack.ui.main.HomeScreen
import com.leo.demo.jetpack.ui.main.ME_SCREEN_ROUTE
import com.leo.demo.jetpack.ui.main.MeScreen
import com.leo.demo.jetpack.ui.main.SHOPPING_SCREEN_ROUTE
import com.leo.demo.jetpack.ui.main.ShopCarScreen

private const val TAG = "ZJL"
private val items = MainScreenNav.items

private fun getSlideDirection(
    initialRoute: String?,
    targetRoute: String?
): AnimatedContentTransitionScope.SlideDirection {
    val initialIndex = items.map { it.route }.indexOf(initialRoute)
    val targetIndex = items.map { it.route }.indexOf(targetRoute)

    return when {
        initialIndex > targetIndex -> AnimatedContentTransitionScope.SlideDirection.End
        initialIndex < targetIndex -> AnimatedContentTransitionScope.SlideDirection.Start
        else -> AnimatedContentTransitionScope.SlideDirection.Start
    }
}

@SuppressLint("ComposableDestinationInComposeScope")
fun NavGraphBuilder.composableSlideInOut(
    route: String,
    screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = {
            slideIntoContainer(
                getSlideDirection(initialState.destination.route, targetState.destination.route),
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                getSlideDirection(initialState.destination.route, targetState.destination.route),
                animationSpec = tween(700)
            )
        },
        content = screen
    )
}

sealed class MainScreenNav(
    val route: String,
    val image: ImageVector,
    @StringRes val resourceId: Int
) {
    companion object {
        val items = listOf(
            Home,
            Category,
            ShopCar,
            Me
        )

        @Composable
        fun loadNavHost(controller: NavHostController, modifier: Modifier) {
            NavHost(
                navController = controller,
                startDestination = Home.route,
                modifier = modifier
            ) {
                composableSlideInOut(Home.route) { HomeScreen() }
                composableSlideInOut(Category.route) { CategoryScreen() }
                composableSlideInOut(ShopCar.route) { ShopCarScreen() }
                composableSlideInOut(Me.route) { MeScreen() }
            }
        }
    }

    object Home : MainScreenNav(HOME_SCREEN_ROUTE, Icons.Filled.Favorite, R.string.main_nav_home)
    object Category :
        MainScreenNav(CATEGORY_SCREEN_ROUTE, Icons.Filled.AccountBox, R.string.main_nav_category)

    object ShopCar :
        MainScreenNav(SHOPPING_SCREEN_ROUTE, Icons.Filled.ShoppingCart, R.string.main_nav_shopcar)

    object Me : MainScreenNav(ME_SCREEN_ROUTE, Icons.Filled.AccountBox, R.string.main_nav_me)
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
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.image, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
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

