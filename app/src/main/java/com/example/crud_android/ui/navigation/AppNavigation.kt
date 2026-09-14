package com.example.crud_android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.crud_android.ui.screen.ProductEditScreen
import com.example.crud_android.ui.screen.ProductScreen

sealed class Screen(val route: String) {
    object ProductList : Screen("product_list")
    object ProductEdit : Screen("product_edit/{productId}") {
        fun createRoute(productId: Int) = "product_edit/$productId"
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
                onEditNavigate = { id ->
                    navController.navigate(Screen.ProductEdit.createRoute(id))
                }
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
    }
}
