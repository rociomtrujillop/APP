package com.example.appestafa.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Shield
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Constante para la ruta de la pantalla de detalle.
 */
internal const val ROUTE_SCAM_DETAIL = "scam_detail"

/**
 * Clase sellada que define todas las pantallas de la app.
 */
sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Scams : Screen(
        route = "scams",
        title = "Estafas",
        icon = Icons.Default.Shield
    )

    data object Guides : Screen(
        route = "guides",
        title = "Guías",
        icon = Icons.AutoMirrored.Filled.LibraryBooks
    )

    data object Help : Screen(
        route = "help",
        title = "Ayuda",
        icon = Icons.AutoMirrored.Filled.HelpOutline
    )
}

/**
 * Define los 3 items que van en la barra de navegación inferior.
 */
val bottomNavItems = listOf(
    Screen.Scams,
    Screen.Guides,
    Screen.Help,
)