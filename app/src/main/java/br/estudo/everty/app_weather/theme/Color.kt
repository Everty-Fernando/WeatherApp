package br.estudo.everty.app_weather.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import br.estudo.everty.app_weather.R

val LightThemeColors: ColorScheme @Composable get() = lightColorScheme(
    primary = colorResource(id = R.color.light_primary),
    onPrimary = colorResource(id = R.color.light_onPrimary),
    onSecondary = colorResource(id = R.color.light_onSecondary),
)

val DarkThemeColors: ColorScheme @Composable get() = darkColorScheme(
    primary = colorResource(id = R.color.dark_primary),
    onPrimary = colorResource(id = R.color.dark_onPrimary),
    onSecondary = colorResource(id = R.color.dark_onSecondary),
)

@Immutable
data class ExtendedColors(
    val white10: Color,
    val black10: Color,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        white10 = Color.Unspecified,
        black10 = Color.Unspecified,
    )
}

val lightExtendedColors: ExtendedColors
    @Composable
    get() = ExtendedColors(
        white10 = colorResource(id = R.color.white10),
        black10 = colorResource(id = R.color.black10),
    )

val darkExtendedColors: ExtendedColors
    @Composable
    get() = ExtendedColors(
        white10 = colorResource(id = R.color.white10),
        black10 = colorResource(id = R.color.black10),
    )