package br.com.everty.home.home_ui.ui.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.everty.home.home_ui.model.FilterType
import br.com.everty.shared.presentation.R
import br.com.everty.shared.presentation.design_system.theme.AppSpacing
import br.com.everty.shared.presentation.design_system.theme.AppWeatherExtended
import br.com.everty.shared.presentation.design_system.theme.lightExtendedColors

@Composable
fun HomeFilters(
    filterSelected: FilterType,
    onFilterWeeklySelected: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = AppSpacing.base),
        horizontalArrangement = Arrangement.Center
    ) {
        FilterHome(
            text = stringResource(R.string.filter_daily),
            selected = filterSelected == FilterType.DAILY,
            onFilterChanged = {
                onFilterWeeklySelected(false)
            }
        )

        Spacer(modifier = Modifier.width(AppSpacing.regular))

        FilterHome(
            text = stringResource(R.string.filter_weekly),
            selected = filterSelected == FilterType.WEEKLY,
            onFilterChanged = {
                onFilterWeeklySelected(true)
            }
        )
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