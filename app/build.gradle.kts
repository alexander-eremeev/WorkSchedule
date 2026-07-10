import androidx.room.gradle.RoomExtension
import java.io.FileInputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties

plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.jetbrains.kotlin.plugin.compose)

    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

    id("androidx.room")                         // Plugin для настройки параметров компилятора Room

}

android {
//    namespace = "com.childmathematics.android.workschedule"
    namespace = libs.versions.applicationId.get()
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.childmathematics.android.workschedule"
        namespace = libs.versions.applicationId.get()
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}
extensions.configure<RoomExtension> {
    // The schemas directory contains a schema file for each version of the Room database.
    // This is required to enable Room auto migrations.
    // В каталоге schemas находится файл схемы для каждой версии базы данных Room.
    // Это необходимо для включения автоматической миграции Room.
    // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
//--------------------------------------------------------------------------
    implementation( libs.dev.chrisbanes.snapper.snapper)    //?????? Snapper в настоящее время устарел,
    // поскольку его функционал заменен на SnapFlingBehavior,
    // доступный в Jetpack Compose 1.3.0.

    // Native Development Kit (NDK) — это набор инструментов, позволяющий использовать код C и C++ с Android.
    // Для отладки собственного кода необходимы собственные символы отладки.
//    implementation(libs.android.tools.build)
    /////////////
    // UI SUPPORT
    // ////
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)

    // Kotlin Coroutines
    implementation(libs.jetbrains.kotlin.coroutines.core)
    implementation(libs.jetbrains.kotlin.coroutines.android)

    // Hilt
    implementation(libs.jetbrains.kotlin.metadata)
    implementation(libs.google.dagger.hilt)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.core.i18n)
    //testImplementation(libs.junit.test)
    ksp(libs.google.dagger.hilt.compiler )

    // Webkit
    implementation(libs.androidx.webkit.webkit)

    implementation(libs.google.accompanist.swiperefresh)
    implementation(libs.google.accompanist.systemuicontroller)

    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)
    //---------------------------------------------
    // for Play In-App Update
    implementation(libs.google.play.appupdate)
    implementation(libs.google.play.appupdate.ktx)
    // ---------------------------------------------------------------------
    // Compose
    // For Compose runtime by default coroutine runtime already included from ui, foundation, implicitly
    // Not able to get rid of material lib due to we still use these component and not available yet in material3
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.window.size)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    // песочница улучшения конфиденциальности пользователей
    implementation(libs.androidx.privacysandbox.tools.core)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    implementation(libs.splashscreen)


    // /////////////
    // DATA SUPPORT
    // ////
    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)            // Kotlin Symbol Processing (KSP)
    implementation(libs.androidx.room.ktx)      //Kotlin Extensions and Coroutines support for Room
    //implementation(libs.androidx.room.common)
    // /////////////
    // ADS SUPPORT
    // ////
    implementation(libs.google.ads)
//    implementation(libs.yandex.mobileads)
//        implementation(libs.yandex.mobmetrica)
//        implementation(libs.yandex.appmetrica)

    // /////////////
    // TEST AND DEBUG SUPPORT
    // ////
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit.ktx)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    testImplementation(libs.jetbrains.test.coroutines)
//    testImplementation(libs.junit.test)
//    androidTestImplementation(libs.androidx.test.ext.junit)
//    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
fun getDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return "\"" + LocalDateTime.now().format(formatter) + "\""

//========================================================================

}