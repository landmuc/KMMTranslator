package com.landmuc.kmmtranslator.android.translate.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// use composed {} for stateful custom modifiers
fun Modifier.gradientSurface(): Modifier = composed {
    if (isSystemInDarkTheme()) {
       this.then(
           Modifier.background(
               brush = Brush.verticalGradient(
                   colors = listOf(
                       Color(0xFF23262E),
                       Color(0xFF212329)
                   )
               )
           )
       )
    } else this.then(Modifier.background(MaterialTheme.colors.surface))
}