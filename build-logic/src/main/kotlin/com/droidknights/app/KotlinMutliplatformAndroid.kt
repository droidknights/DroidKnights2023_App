package com.droidknights.app

import com.android.build.gradle.TestExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureKotlinMultiplatformAndroid() {
    with(pluginManager) {
        apply("com.android.library")
    }

    kotlin {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "17"
                }
            }
        }
    }

    extensions.configure<TestExtension> {
        configureKotlinAndroid()
        sourceSets {
            getByName("main") {
                assets.srcDirs("src/androidMain/assets")
                java.srcDirs("src/androidMain/kotlin", "src/commonMain/kotlin")
                res.srcDirs("src/androidMain/res")
            }
            getByName("test") {
                assets.srcDirs("src/androidUnitTest/assets")
                java.srcDirs("src/androidUnitTest/kotlin", "src/commonTest/kotlin")
                res.srcDirs("src/androidUnitTest/res")
            }
        }
    }
}