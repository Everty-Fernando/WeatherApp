package br.estudo.everty.app_weather.home.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import br.com.everty.shared.presentation.design_system.theme.AppSpacing
import br.com.everty.shared.presentation.R
import br.com.everty.home.home_domain.model.WeatherDataUI
import br.com.everty.shared.presentation.design_system.theme.AppCustomTypography
import br.com.everty.shared.presentation.design_system.theme.AppWeatherExtended
import br.com.everty.shared.presentation.design_system.theme.lightExtendedColors

@Composable
fun HomeSummaryWeatherContent(weatherDataUI: WeatherDataUI) {
    Column(modifier = Modifier
        .padding(AppSpacing.regular)
        .fillMaxWidth()
        .clip(RoundedCornerShape(AppSpacing.semi_base))
        .background(lightExtendedColors.white10)
    ) {

        Column(modifier = Modifier.padding(top = AppSpacing.regular)) {
            Row(Modifier.padding(horizontal = AppSpacing.regular)) {
                Text(
                    text = stringResource(R.string.home_summary),
                    modifier = Modifier.weight(1f),
                    style = AppWeatherExtended.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(text = weatherDataUI.date, style = AppCustomTypography.labelLarge)
            }

            LazyVerticalGrid(modifier = Modifier.padding(AppSpacing.regular), columns = GridCells.Fixed(3)) {
                items(weatherDataUI.listSummary) { itemSummary ->
                    ItemSummary(
                        infoTitle = itemSummary.title,
                        infoValue = itemSummary.value,
                        icon = itemSummary.icon
                    )
                }
            }
        }
    }
}

@Composable
fun ItemSummary(infoTitle: String, infoValue: String, icon: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = AppSpacing.small)
    ) {
        Icon(
            modifier = Modifier.size(AppSpacing.semi_base),
            painter = painterResource(id = icon),
            tint = Color.White,
            contentDescription = ""
        )
        Text(
            text = infoTitle,
            style = AppWeatherExtended.typography.labelSmall
        )
        Text(
            text = infoValue,
            style = AppWeatherExtended.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}