plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.estudo.everty.app_weather"
    compileSdk = ApplicationConfig.compileSdkVersion

    defaultConfig {
        applicationId = ApplicationConfig.id
        minSdk = ApplicationConfig.minSdk
        targetSdk = ApplicationConfig.targetSdk
        versionCode = Releases.versionCode
        versionName = Releases.versionName
        android.buildFeatures.buildConfig = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
        val API_KEY = "e5b9e97f0cdc5586bab24debec508038"
        buildConfigField("String", "API_KEY", "\"${API_KEY}\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(SharedModules.presentation))
    implementation(project(SharedModules.network))
    implementation(project(SharedModules.utils))

    implementation(project(FeatureModules.homeData))
    implementation(project(FeatureModules.homeDomain))
    implementation(project(FeatureModules.homeUI))

    implementation(platform(Compose.composeBom))
    implementation(Compose.uiTooling)
    implementation(Compose.material3)
    implementation(Compose.activity)
    implementation(Compose.lottie)

    implementation(AndroidX.coordinatorlayout)
    implementation(AndroidX.androidCore)
    implementation(AndroidX.lifecycleRuntime)

    implementation(Jetpack.navigationUi)
    implementation(Jetpack.navigationFragment)

    implementation(Koin.koinAndroid)
}