plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.compose)
}

kotlin {
    androidLibrary {
        namespace = "de.malteans.datastore"
        compileSdk = 37
        minSdk = 30
    }

    jvm("desktop")

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "dataStoreKit"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }

        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)

            implementation(libs.bundles.compose)
            implementation(libs.compose.material3)

            api(libs.datastore)
            api(libs.datastore.preferences)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

        iosMain.dependencies {

        }
    }
}