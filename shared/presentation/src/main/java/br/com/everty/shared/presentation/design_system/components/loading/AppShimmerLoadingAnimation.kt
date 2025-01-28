package br.com.everty.shared.presentation.design_system.components.loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.everty.shared.presentation.design_system.theme.AppWeatherTheme


@Composable
fun ShimmerAnimation(shimmerItem: @Composable (Brush) -> Unit) {
    val shimmerColorShades = listOf(

        MaterialTheme.colorScheme.background.copy(0.5f),

        MaterialTheme.colorScheme.background.copy(0.2f),

        MaterialTheme.colorScheme.background.copy(0.5f),

        )

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing), RepeatMode.Reverse
        ), label = ""
    )


    val brush = Brush.linearGradient(
        colors = shimmerColorShades, start = Offset(10f, 10f), end = Offset(translateAnim, translateAnim)
    )

    shimmerItem(brush)

}

@Composable
fun ShimmerItemTwoLines(
    brush: Brush, firstLineWidth: Dp = 400.dp, secondLineWidth: Dp = 400.dp,
    firstLineHeight: Dp = 40.dp,
    secondLineHeight: Dp = 40.dp,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .width(firstLineWidth)
                .clip(RoundedCornerShape(10.dp))
                .height(firstLineHeight)
                .background(brush = brush)
        )

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .width(secondLineWidth)
                .clip(RoundedCornerShape(10.dp))
                .height(secondLineHeight)
                .background(brush = brush)
        )
    }
}


@Composable
fun ShimmerItemRectangle(brush: Brush, rectangleSize: Dp = 250.dp, roundedCorner: Dp = 10.dp, modifier: Modifier = Modifier) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(roundedCorner))
                .size(rectangleSize)
                .background(brush = brush)
        )
    }
}


@Composable
fun ShimmerItemCircle(brush: Brush, circleSize: Dp = 60.dp, modifier: Modifier = Modifier) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .size(circleSize)
                .background(brush = brush)
        )
    }
}



@Composable
fun ShimmerItemRectangleWithBottomSpacer(brush: Brush, rectangleSize: Dp = 250.dp) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .size(rectangleSize)
                .background(brush = brush)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .height(30.dp)
                .padding(vertical = 8.dp)
                .background(brush = brush)
        )
    }
}


@Composable
@Preview
fun ShimmerItemRectangle_Preview() {
    AppWeatherTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            ShimmerAnimation {
                ShimmerItemRectangle(brush = it)
            }
        }

    }
}

@Composable
@Preview
fun ShimmerItemRectangleBottomSpacer_Preview() {
    AppWeatherTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            ShimmerAnimation {
                ShimmerItemRectangleWithBottomSpacer(brush = it)
            }
        }

    }
}


@Composable
@Preview
fun ShimmerItemTwoLines_Preview() {
    AppWeatherTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            ShimmerAnimation {
                ShimmerItemTwoLines(brush = it)
            }
        }

    }
}