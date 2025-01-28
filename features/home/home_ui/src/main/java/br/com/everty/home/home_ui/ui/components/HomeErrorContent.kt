package br.com.everty.home.home_ui.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.everty.home.home_ui.model.ErrorState
import br.com.everty.shared.presentation.design_system.components.error.AppErrorContent
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme

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
        HomeErrorContent(errorState = ErrorState.LocationGetError, onClick = {})
    }
}