object Compose {
    private const val composeBomVersion = "2023.03.00"
    private const val composeCompilerVersion = "1.4.4"

    private const val composeActivitiesVersion = "1.8.2"
    private const val composeLottieVersion = "5.2.0"

    const val composeBom = "androidx.compose:compose-bom:$composeBomVersion"

    const val uiTooling = "androidx.compose.ui:ui-tooling-preview"

    const val material3 = "androidx.compose.material3:material3"
    const val lottie = "com.airbnb.android:lottie-compose:$composeLottieVersion"

    const val activity = "androidx.activity:activity-compose:${composeActivitiesVersion}"
}