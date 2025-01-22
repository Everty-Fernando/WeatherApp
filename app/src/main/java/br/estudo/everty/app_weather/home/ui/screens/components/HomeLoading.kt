package br.estudo.everty.app_weather.home.ui.screens.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.everty.shared.presentation.design_system.theme.AppSpacing
import br.com.everty.shared.presentation.design_system.components.loading.ShimmerAnimation
import br.com.everty.shared.presentation.design_system.components.loading.ShimmerItemCircle
import br.com.everty.shared.presentation.design_system.components.loading.ShimmerItemRectangle
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme

@ExperimentalAnimationApi
@Composable
fun HomeLoading() {
    Column {
        ShimmerAnimation { brush ->
            Box(modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = AppSpacing.xlarge)
                .width(150.dp)) {
                ShimmerItemRectangle(brush = brush, rectangleSize = 24.dp)
            }

            Box(modifier = Modifier
                .align(CenterHorizontally)
                .padding(AppSpacing.micro)
                .width(130.dp)) {
                ShimmerItemRectangle(brush = brush, rectangleSize = 12.dp)
            }


            Spacer(modifier = Modifier.height(AppSpacing.base))

            Row(modifier = Modifier
                .align(CenterHorizontally)
                .height(135.dp)
                .padding(horizontal = AppSpacing.base), horizontalArrangement = Arrangement.spacedBy(
                AppSpacing.regular)) {
                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(130.dp)) {
                   ShimmerItemCircle(brush = brush, circleSize = 130.dp)
               }

                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(173.dp)) {
                    ShimmerItemCircle(brush = brush, circleSize = 135.dp)
                }
            }

            Box(modifier = Modifier
                .align(CenterHorizontally)
                .padding(
                    top = AppSpacing.small,
                    start = AppSpacing.regular,
                    end = AppSpacing.regular
                )
                .width(130.dp)) {
                ShimmerItemRectangle(brush = brush, rectangleSize = 12.dp)
            }

            Spacer(modifier = Modifier.height(AppSpacing.base))

            Box(modifier = Modifier.padding(horizontal = AppSpacing.regular)) {
                ShimmerItemRectangle(brush = brush, rectangleSize = 200.dp, roundedCorner = AppSpacing.semi_base)
            }

            Spacer(modifier = Modifier.height(AppSpacing.base))

            LazyRow(modifier = Modifier
                .padding(start = AppSpacing.mini)
                .align(CenterHorizontally)) {
                repeat(2) {
                    item {
                        Box(modifier = Modifier
                            .padding(AppSpacing.mini)
                            .width(90.dp)) {
                            ShimmerItemCircle(brush = brush, 40.dp)
                        }
                    }
                }
            }

            HomeFooterLoading()
        }
    }
}


@Composable
fun HomeFooterLoading() {
    Column {
        Spacer(modifier = Modifier.height(AppSpacing.large))

        ShimmerAnimation { brush ->
            LazyRow(modifier = Modifier
                .padding(start = AppSpacing.mini)
                .align(CenterHorizontally)) {
                repeat(5) {
                    item {
                        Box(modifier = Modifier
                            .padding(AppSpacing.mini)
                            .width(80.dp)
                            .height(139.dp)) {
                            ShimmerItemCircle(brush = brush, 150.dp)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview
fun NotificationListLoading_Preview() {
    AppWeatherTheme {
        HomeLoading()
    }
}