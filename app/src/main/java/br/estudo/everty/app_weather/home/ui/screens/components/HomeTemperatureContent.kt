package br.estudo.everty.app_weather.home.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.everty.shared.presentation.design_system.theme.AppSpacing
import br.estudo.everty.app_weather.R
import br.com.everty.shared.presentation.design_system.theme.AppWeatherExtended


@Composable
fun HomeTemperatureContent(temperature: String, description: String, image: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppSpacing.base),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.mini)
        ) {
            Image(
                modifier = Modifier
                    .width(135.dp)
                    .height(130.dp),
                painter = painterResource(id = image),
                contentDescription = "Sun with cloud"
            )

            Text(
                modifier = Modifier.align(Alignment.Bottom),
                text = temperature,
                style =  AppWeatherExtended.typography.titleLarge
            )
        }
        Text(
            modifier = Modifier.padding(top = AppSpacing.regular),
            text = description,
            style = AppWeatherExtended.typography.titleSmall
        )
    }
}