package br.estudo.everty.app_weather.home.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.estudo.everty.app_weather.theme.AppSpacing
import br.estudo.everty.app_weather.home.domain.model.WeatherTimelineUI
import br.estudo.everty.app_weather.theme.AppCustomTypography
import br.estudo.everty.app_weather.theme.AppWeatherExtended
import br.estudo.everty.app_weather.theme.backgrounds.BackgroundGradientTransparent

@Composable
fun HomeFooter(weatherList: List<WeatherTimelineUI>) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppSpacing.large),
            horizontalArrangement = Arrangement.spacedBy(AppSpacing.mini),
            contentPadding = PaddingValues(
                start = AppSpacing.regular,
                end = AppSpacing.regular
            )
        ) {
            items(weatherList) {item ->
                ItemFooter(item.date, item.temperature, item.icon)
            }
        }
    }
}

@Composable
fun ItemFooter(time: String, temperature: String, image: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppSpacing.regular),
        modifier = Modifier
            .clip(CircleShape)
            .background(brush = BackgroundGradientTransparent())
            .padding(AppSpacing.regular)
    ) {
        Text(text = time, style = AppCustomTypography.bodyLarge)
        Image(
            modifier = Modifier.size(AppSpacing.xlarge),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = image),
            contentDescription = "Sun with cloud",
        )
        Text(text = temperature, style = AppWeatherExtended.typography.bodySmall)
    }
}