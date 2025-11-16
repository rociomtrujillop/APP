package com.example.appestafa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appestafa.R // ¡Importante para los íconos!

/**
 * Pantalla "Ayuda y Denuncias" (AyudaDenuncias.jpg)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ayuda y denuncias", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    // El botón de atrás es opcional en la pantalla principal
                    // Lo dejamos por si decides mover esta pantalla
                    IconButton(onClick = { /* No hace nada en la raíz */ }) {
                        // Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Aplica el padding de la TopBar
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // --- Banner de Advertencia ---
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE0F7FA)) // Un color azul claro
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_advertencia), // ¡Agrega este ícono!
                        contentDescription = "Peligro",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "Si estás en peligro, llama a emergencias de inmediato.",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // --- Tarjetas de Contacto ---
            item {
                HelpContactCard(
                    title = "Emergencias",
                    description = "Inmediata con la policía o servicios de urgencia.",
                    note = "# 911",
                    icon = Icons.Default.Call,
                    buttonText = "Llamar",
                    onButtonClick = { /* TODO: Intent de llamada */ }
                )
            }
            item {
                HelpContactCard(
                    title = "Policía local",
                    description = "Para reportar fraude o estafa en tu ciudad.",
                    note = "Número no emergencias",
                    icon = Icons.Default.Search,
                    buttonText = "Buscar",
                    isOutlinedButton = true,
                    onButtonClick = { /* TODO: Intent de búsqueda */ }
                )
            }
            item {
                HelpContactCard(
                    title = "Banco (fraudes)",
                    description = "Línea de atención para bloquear tarjetas y reportar cargos.",
                    note = "Número al reverso de tu tarjeta",
                    icon = Icons.Default.CreditCard,
                    buttonText = "Ver tarjetas",
                    isOutlinedButton = true,
                    onButtonClick = { /* TODO: */ }
                )
            }
            item {
                HelpContactCard(
                    title = "Agencia de consumo",
                    description = "Reporta estafas y prácticas engañosas a protección al consumidor.",
                    note = "800-000-000",
                    icon = Icons.AutoMirrored.Filled.LibraryBooks,
                    buttonText = "Llamar",
                    onButtonClick = { /* TODO: Intent de llamada */ }
                )
            }
            item {
                HelpContactCard(
                    title = "Centro cibernético",
                    description = "Para delitos en línea: suplantación, phishing, extorsión digital.",
                    note = "888-123-4567",
                    icon = Icons.Default.Shield,
                    buttonText = "Enviar reporte",
                    isOutlinedButton = true,
                    onButtonClick = { /* TODO: Intent de enlace web */ }
                )
            }
            item {
                HelpContactCard(
                    title = "Operador telefónico",
                    description = "Bloqueo de números y mensajes spam o suplantación.",
                    note = "# 666 (SPAM)",
                    icon = Icons.Default.Sms,
                    buttonText = "Enviar SMS",
                    isOutlinedButton = true,
                    onButtonClick = { /* TODO: Intent de SMS */ }
                )
            }

            // --- Consejos Rápidos ---
            item {
                Text(
                    "Consejos rápidos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE8F5E9)) // Un color verde claro
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = "Consejo",
                        tint = Color(0xFF2E7D32), // Verde oscuro
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "Ten a mano tus datos, fecha y descripción del hecho. No compartas PIN ni contraseñas.",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 22.sp
                    )
                }
            }

            // Espacio al final
            item {
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

/**
 * Plantilla para una tarjeta de contacto de ayuda
 */
@Composable
fun HelpContactCard(
    title: String,
    description: String,
    note: String,
    icon: ImageVector,
    buttonText: String,
    isOutlinedButton: Boolean = false,
    onButtonClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(8.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(Modifier.width(16.dp))

            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))
                Text(description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray, lineHeight = 20.sp)
                Spacer(Modifier.height(8.dp))
                Text(note, style = MaterialTheme.typography.bodySmall, color = Color.Gray, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.width(16.dp))

            if (isOutlinedButton) {
                OutlinedButton(
                    onClick = onButtonClick,
                    shape = RoundedCornerShape(100.dp),
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(buttonText)
                }
            } else {
                Button(
                    onClick = onButtonClick,
                    shape = RoundedCornerShape(100.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(buttonText)
                }
            }
        }
    }
}