package com.example.appestafa.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appestafa.R
import com.example.appestafa.data.ScamDetail
import com.example.appestafa.data.StepItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScamDetailScreen(
    scamDetail: ScamDetail?,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    var showReportDialog by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }

    if (showReportDialog) {
        AlertDialog(
            onDismissRequest = { showReportDialog = false },
            title = { Text("Reportar Estafa") },
            text = {
                Column {
                    Text("Si tienes el número de teléfono del estafador, puedes incluirlo en el reporte.")
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Número de teléfono (Opcional)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val emailBody = buildString {
                            append("A continuación, describa el incidente:\n\n\n")
                            if (phoneNumber.isNotBlank()) {
                                append("-----------------------------------\n")
                                append("Número de teléfono del estafador: $phoneNumber\n")
                            }
                        }

                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("reportes@ejemplo.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "Reporte de Estafa: ${scamDetail?.title}")
                            putExtra(Intent.EXTRA_TEXT, emailBody)
                        }
                        context.startActivity(Intent.createChooser(intent, "Enviar Reporte"))
                        showReportDialog = false
                    }
                ) {
                    Text("Enviar Reporte")
                }
            },
            dismissButton = {
                TextButton(onClick = { showReportDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = scamDetail?.title ?: "Error",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        bottomBar = {
            if (scamDetail != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (scamDetail.showBankButton) {
                        OutlinedButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_DIAL)
                                context.startActivity(intent)
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Llamar a mi banco")
                        }
                    }
                    Button(
                        onClick = { showReportDialog = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Reportar estafa")
                    }
                }
            }
        }
    ) { innerPadding ->
        if (scamDetail != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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
                item { DetailSection("Ejemplo", scamDetail.example) }
                item { DetailSection("Qué debo hacer si soy víctima", scamDetail.whatToDo) }
                item { Spacer(Modifier.height(16.dp)) }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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

@Composable
fun DetailSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp)
    )
}

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

@Composable
fun DetailSection(title: String, items: List<StepItem>) {
    DetailSectionTitle(title = title)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            items.forEach { item ->
                StepItemRow(item = item)
            }
        }
    }
}

@Composable
fun DetailSection(title: String, text: String) {
    DetailSectionTitle(title = title)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp
        )
    }
}
