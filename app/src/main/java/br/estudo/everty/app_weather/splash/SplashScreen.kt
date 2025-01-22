package br.estudo.everty.app_weather.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme
import br.com.everty.shared.presentation.design_system.theme.backgrounds.BackgroundGradientLinearPrimary
import br.estudo.everty.app_weather.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen(onNavigateToHome: () -> Unit) {
    val rawComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_loading))
    val splashAnimation = animateLottieCompositionAsState(composition = rawComposition)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = BackgroundGradientLinearPrimary()),
    ) {
        LottieAnimation(
            composition = rawComposition,
            progress = { splashAnimation.progress },
        )

        if (splashAnimation.isAtEnd && splashAnimation.isPlaying) {
            onNavigateToHome()
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    AppWeatherTheme {
        SplashScreen(onNavigateToHome = { })
    }
}