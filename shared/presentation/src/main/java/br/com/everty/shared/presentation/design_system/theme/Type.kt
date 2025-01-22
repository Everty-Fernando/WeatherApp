package br.com.everty.shared.presentation.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import br.com.everty.shared.presentation.R


val circularStdFamily = FontFamily(
    Font(R.font.circular_std_bold, FontWeight.Bold),
    Font(R.font.circular_std_medium, FontWeight.Medium),
    Font(R.font.circular_std_book, FontWeight.Normal),
    Font(R.font.circular_std_light, FontWeight.Light)
)

// Set of Material typography styles to start with
val AppDefaultTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = circularStdFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = circularStdFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = circularStdFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)

object AppCustomTypography {

    val titleLarge: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = circularStdFamily,
            fontWeight = FontWeight.Light,
            fontSize = 80.sp
        )

    val titleMedium: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleSmall.copy(
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = circularStdFamily,
            fontWeight = FontWeight.Normal,
            lineHeight = TextUnit(32f, TextUnitType.Sp),
            fontSize = 32.sp,
        )

    val titleSmall: TextStyle
        @Composable
        get() = MaterialTheme.typography.titleSmall.copy(
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.Normal,
            fontFamily = circularStdFamily,
            fontSize = 18.sp,
        )

    val bodyLarge: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = circularStdFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )

    val bodySmall: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = circularStdFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        )

    val labelLarge: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = circularStdFamily,
            fontSize = 12.sp,
        )

    val labelSmall: TextStyle
        @Composable
        get() = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.onSecondary,
            fontFamily = circularStdFamily,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp,
        )

}
