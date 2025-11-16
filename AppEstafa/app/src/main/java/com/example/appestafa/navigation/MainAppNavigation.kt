package com.example.appestafa.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appestafa.ui.screens.GuidesScreen
import com.example.appestafa.ui.screens.HelpScreen
import com.example.appestafa.ui.screens.ScamDetailScreen
import com.example.appestafa.ui.screens.ScamListScreen
import com.example.appestafa.ui.viewmodel.ScamViewModel

@Composable
fun MainAppNavigation() {
    // 1. Crea el controlador de navegación
    val navController: NavHostController = rememberNavController()

    // 2. Obtiene una instancia del "cerebro" (ViewModel)
    val scamViewModel: ScamViewModel = viewModel()

    // 3. Crea el "esqueleto" de la app
    Scaffold(
        bottomBar = {
            // --- Barra de Navegación Inferior ---
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                // Itera sobre los items (Estafas, Guías, Ayuda)
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // --- Área de Contenido Principal (El "Escenario") ---
        NavHost(
            navController = navController,
            startDestination = Screen.Scams.route, // Empieza en "Estafas"
            modifier = Modifier.padding(innerPadding) // Aplica el padding del Scaffold
        ) {

            // --- Pantalla 1: Lista de Estafas ---
            composable(Screen.Scams.route) {
                val scams by scamViewModel.scams.collectAsState()
                ScamListScreen(
                    scams = scams,
                    onScamClick = { scamId ->
                        // Navega a la pantalla de detalle
                        navController.navigate("$ROUTE_SCAM_DETAIL/$scamId")
                    }
                )
            }

            // --- Pantalla 2: Guías ---
            composable(Screen.Guides.route) {
                GuidesScreen()
            }

            // --- Pantalla 3: Ayuda ---
            composable(Screen.Help.route) {
                HelpScreen()
            }

            // --- Pantalla 4: Detalle de Estafa ---
            composable(
                route = "$ROUTE_SCAM_DETAIL/{scamId}", // Define una ruta con un argumento
                arguments = listOf(navArgument("scamId") { })
            ) { backStackEntry ->
                val scamId = backStackEntry.arguments?.getString("scamId") ?: ""
                val detail = scamViewModel.getScamDetailById(scamId)

                ScamDetailScreen(
                    scamDetail = detail,
                    onNavigateBack = {
                        navController.popBackStack() // Acción para el botón de "atrás"
                    }
                )
            }
        }
    }
}