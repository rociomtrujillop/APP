package com.example.appestafa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appestafa.R
import com.example.appestafa.data.StepItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuidesScreen() { // Se elimina el parámetro onNavigateBack

    val generalTips = listOf(
        StepItem("Desconfía de ofertas o premios inesperados. Si suena demasiado bueno para ser verdad, probablemente no lo sea.", R.drawable.ic_premio),
        StepItem("Mantén la calma ante amenazas o urgencias. Los estafadores usan la presión para que no pienses con claridad.", R.drawable.ic_urgencia),
        StepItem("Verifica la identidad de quien te contacta por un canal oficial. Cuelga y llama tú mismo al número que SÍ conoces.", R.drawable.ic_denuncia),
        StepItem("Nunca compartas contraseñas, códigos de verificación o datos de tu tarjeta. Tu banco JAMÁS te pedirá esa información.", R.drawable.ic_contrasena),
        StepItem("No hagas clic en enlaces de SMS o emails sospechosos. Es mejor escribir la dirección web directamente en el navegador.", R.drawable.ic_link),
        StepItem("Activa la autenticación de dos factores (2FA) en todas tus cuentas importantes (banco, email, redes sociales).", R.drawable.ic_sim)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Guía de Prevención") } // Se elimina el navigationIcon
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 8.dp)
        ) {
            items(generalTips) { tip ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = tip.iconResId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(6.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(
                            text = tip.text,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 22.sp
                        )
                    }
                }
            }
        }
    }
}