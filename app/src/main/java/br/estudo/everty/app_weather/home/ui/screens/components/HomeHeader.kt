package br.estudo.everty.app_weather.home.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.everty.shared.presentation.design_system.theme.AppSpacing
import br.com.everty.shared.presentation.design_system.theme.AppWeatherExtended
import br.estudo.everty.app_weather.R

@Composable
fun HomeHeader(cityName: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = AppSpacing.xlarge), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = cityName, style = AppWeatherExtended.typography.titleMedium)
        Row {
            Icon(
                modifier = Modifier.size(AppSpacing.regular),
                painter = painterResource(id = R.drawable.ic_location),
                tint = Color.White,
                contentDescription = "Location icon"
            )
            Text(text = stringResource(R.string.current_location), style = AppWeatherExtended.typography.labelLarge)
        }
    }
}