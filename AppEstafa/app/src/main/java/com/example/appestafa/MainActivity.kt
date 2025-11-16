package com.example.appestafa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appestafa.navigation.MainAppNavigation
import com.example.appestafa.ui.theme.AppEstafaTheme // ¡Asegúrate que este sea el nombre de tu tema!

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Para que la app use la pantalla completa (gestos)
        setContent {
            // Carga tu tema (colores, fuentes)
            AppEstafaTheme {
                // Llama al Composable principal que controla toda la app
                MainAppNavigation()
            }
        }
    }
}