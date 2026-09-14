package com.example.crud_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.crud_android.ui.screen.ProductCreateScreen
import com.example.crud_android.ui.screen.ProductDeleteScreen
import com.example.crud_android.ui.screen.ProductEditScreen
import com.example.crud_android.ui.screen.ProductScreen
import com.example.crud_android.ui.screen.ProductSearchScreen

sealed class Screen(val route: String) {
    object ProductList : Screen("product_list")
    object ProductCreate : Screen("product_create")
    object ProductSearch : Screen("product_search")
    object ProductEdit : Screen("product_edit/{productId}") {
        fun createRoute(productId: Int) = "product_edit/$productId"
    }
    object ProductDelete : Screen("product_delete/{productId}") {
        fun createRoute(productId: Int) = "product_delete/$productId"
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductList.route
    ) {
        composable(Screen.ProductList.route) {
            ProductScreen(
                onNavigateToCreate = { navController.navigate(Screen.ProductCreate.route) },
                onNavigateToSearch = { navController.navigate(Screen.ProductSearch.route) },
                onEditNavigate = { id ->
                    navController.navigate(Screen.ProductEdit.createRoute(id))
                },
                onDeleteNavigate = { id ->
                    navController.navigate(Screen.ProductDelete.createRoute(id))
                }
            )
        }

        composable(Screen.ProductCreate.route) {
            ProductCreateScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.ProductSearch.route) {
            ProductSearchScreen(
                onEditNavigate = { id ->
                    navController.navigate(Screen.ProductEdit.createRoute(id))
                },
                onDeleteNavigate = { id ->
                    navController.navigate(Screen.ProductDelete.createRoute(id))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(
            route = Screen.ProductEdit.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductEditScreen(
                productId = productId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.ProductDelete.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDeleteScreen(
                productId = productId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
