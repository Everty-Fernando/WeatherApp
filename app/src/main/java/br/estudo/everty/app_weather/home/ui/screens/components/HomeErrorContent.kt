package br.estudo.everty.app_weather.home.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.everty.shared.presentation.design_system.components.error.AppErrorContent
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme
import br.estudo.everty.app_weather.home.ui.screens.model.ErrorState

@Composable
fun HomeErrorContent(errorState: ErrorState, onClick: () -> Unit) {
    AppErrorContent(
        titleError = errorState.title,
        descriptionError = errorState.description,
        textButton = errorState.textButton,
        iconError = errorState.icon,
        onClick = onClick
    )
}

@Preview
@Composable
fun HomeErrorContentPreview() {
    AppWeatherTheme {
        HomeErrorContent(errorState = ErrorState.ERROR_SERVER, onClick = {})
    }
}