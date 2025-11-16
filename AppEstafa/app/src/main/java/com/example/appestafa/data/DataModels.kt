package com.example.appestafa.data

import androidx.annotation.DrawableRes

/**
 * Molde para un ítem de la lista principal (ScamInfo.jpg)
 */
data class Scam(
    val id: String,
    val title: String,
    val summary: String,
    @DrawableRes val iconResId: Int // El ID del ícono en res/drawable
)

/**
 * Molde para un ítem de la pantalla de detalle (DetalleEstafa.jpg)
 */
data class ScamDetail(
    val id: String,
    val title: String,
    val type: String,
    @DrawableRes val headerImageResId: Int,
    val howItWorks: List<StepItem>,
    val redFlags: List<StepItem>,
    val example: String,
    val whatToDo: List<StepItem>
)

/**
 * Molde para un solo paso (ej: un ítem en "Señales de alerta")
 */
data class StepItem(
    val text: String,
    @DrawableRes val iconResId: Int
)