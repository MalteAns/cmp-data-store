plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    android {
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
        val desktopMain = getByName("desktopMain")
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