package br.estudo.everty.app_weather.home.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.everty.shared.presentation.design_system.theme.AppSpacing
import br.com.everty.shared.presentation.design_system.theme.AppWeatherExtended
import br.com.everty.shared.presentation.design_system.theme.lightExtendedColors
import br.com.everty.shared.presentation.R

@Composable
fun HomeFilters(onFilterDailySelected: (Boolean) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = AppSpacing.base),
        horizontalArrangement = Arrangement.Center
    ) {
        var selectedDaily by remember { mutableStateOf(true) }
        var selectedWeekly by remember { mutableStateOf(false) }

        FilterHome(text = stringResource(R.string.filter_daily), selected = selectedDaily, onFilterChanged = {
            selectedDaily = !selectedDaily
            selectedWeekly = !selectedDaily
            onFilterDailySelected(selectedDaily)
        })

        Spacer(modifier = Modifier.width(AppSpacing.regular))

        FilterHome(text = stringResource(R.string.filter_weekly),selected = selectedWeekly, onFilterChanged = {
            selectedWeekly = !selectedWeekly
            selectedDaily = !selectedWeekly
            onFilterDailySelected(!selectedWeekly)
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterHome(selected: Boolean, onFilterChanged: () -> Unit, text: String) {
    FilterChip(
        shape = CircleShape,
        border = null,
        selected = selected,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = lightExtendedColors.white10,
            labelColor = MaterialTheme.colorScheme.onSecondary,
            selectedContainerColor = lightExtendedColors.black10
        ),
        onClick = { onFilterChanged() },
        label = {
            Text(
                modifier = Modifier.padding(AppSpacing.small),
                text = text,
                style = AppWeatherExtended.typography.bodySmall
            )
        }
    )
}