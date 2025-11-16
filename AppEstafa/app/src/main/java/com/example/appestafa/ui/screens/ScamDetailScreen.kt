package com.example.appestafa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appestafa.R
import com.example.appestafa.data.ScamDetail
import com.example.appestafa.data.StepItem

/**
 * Pantalla de Detalle de Estafa (DetalleEstafa.jpg)
 * ¡CORREGIDA! Ahora el botón de volver siempre es visible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScamDetailScreen(
    scamDetail: ScamDetail?,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = scamDetail?.title ?: "Error", // Muestra "Error" si no hay título
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    // ¡EL BOTÓN DE VOLVER AHORA SIEMPRE ES VISIBLE!
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        bottomBar = {
            // Solo muestra la barra de botones si la estafa existe
            if (scamDetail != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = { /* TODO: */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Llamar a mi banco")
                    }
                    Button(
                        onClick = { /* TODO: */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Reportar estafa")
                    }
                }
            }
        }
    ) { innerPadding ->

        // Comprueba si la estafa existe
        if (scamDetail != null) {
            // --- Si existe, muestra el detalle ---
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Padding de la TopBar
            ) {
                item {
                    Image(
                        painter = painterResource(id = scamDetail.headerImageResId),
                        contentDescription = scamDetail.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Text(
                        text = scamDetail.type,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.Medium
                    )
                }
                item { DetailSection("Cómo funciona", scamDetail.howItWorks) }
                item { DetailSection("Señales de alerta (red flags)", scamDetail.redFlags) }
                item {
                    DetailSectionTitle(title = "Ejemplo")
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Text(
                            text = scamDetail.example,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp
                        )
                    }
                }
                item { DetailSection("Qué debo hacer si soy víctima", scamDetail.whatToDo) }
                item { Spacer(Modifier.height(16.dp)) }
            }
        } else {
            // --- Si NO existe, muestra el error DENTRO del Scaffold ---
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Respeta el padding
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error: No se encontró el detalle de la estafa. Presiona 'Volver' para continuar.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// --- (El resto de las funciones @Composable de esta pantalla no cambian) ---

/**
 * Un título de sección (ej: "Cómo funciona")
 */
@Composable
fun DetailSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    )
}

/**
 * Un ítem de la lista de pasos (ícono + texto)
 */
@Composable
fun StepItemRow(item: StepItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            painter = painterResource(id = item.iconResId),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(4.dp),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = item.text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 22.sp
        )
    }
}

/**
 * Una sección completa (Título + lista de pasos)
 */
@Composable
fun DetailSection(title: String, items: List<StepItem>) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        DetailSectionTitle(title = title)
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            items.forEach { item ->
                StepItemRow(item = item)
            }
        }
    }
}