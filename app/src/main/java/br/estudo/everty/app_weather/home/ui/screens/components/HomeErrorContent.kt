package br.estudo.everty.app_weather.home.ui.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.estudo.everty.app_weather.home.ui.screens.model.ErrorState
import br.estudo.everty.app_weather.presentation.design_system.components.AppErrorContent
import br.estudo.everty.app_weather.theme.AppWeatherTheme

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