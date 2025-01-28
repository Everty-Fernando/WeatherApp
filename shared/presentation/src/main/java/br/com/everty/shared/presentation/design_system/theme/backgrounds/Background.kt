package br.com.everty.shared.presentation.design_system.theme.backgrounds

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import br.com.everty.shared.presentation.design_system.theme.lightExtendedColors


@Composable
fun BackgroundGradientLinearPrimary() =
    Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.onPrimary
        ),  start = Offset.Infinite,
        end = Offset.Zero
    )


@Composable
fun BackgroundGradientTransparent() =
    Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.onPrimary,
            lightExtendedColors.white10
        )
    )

