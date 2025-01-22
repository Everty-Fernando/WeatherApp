package br.estudo.everty.app_weather.presentation.design_system.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.estudo.everty.app_weather.R
import br.estudo.everty.app_weather.theme.AppCustomTypography
import br.estudo.everty.app_weather.theme.AppSpacing
import br.estudo.everty.app_weather.theme.AppWeatherTheme

@Composable
fun AppErrorContent(
    titleError: String,
    descriptionError: String,
    textButton: String,
    iconError: Int,
    onClick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(AppSpacing.base),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconError),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(AppSpacing.base) )

        Text(
            text = titleError,
            style = AppCustomTypography.titleMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppSpacing.mini) )

        Text(
            text = descriptionError,
            style = AppCustomTypography.bodyLarge.copy(fontWeight = FontWeight.Light),
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier.padding(top = AppSpacing.xlarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.background),
            shape = RoundedCornerShape(50),
            onClick = onClick
        ) {
            Text(
                modifier = Modifier.padding(AppSpacing.mini),
                text = textButton,
                style = AppCustomTypography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun AppErrorContentPreview() {
    AppWeatherTheme {
        AppErrorContent(
            titleError = "Erro no servidor",
            descriptionError = "Lamentamos, ocorreu uma falha ao recuperar as informações meteorológicas. Por favor, verifique sua conexão com a internet e tente novamente mais tarde.",
            textButton = "Tentar novamente",
            R.drawable.generic_error,
            onClick = {}
        )
    }
}