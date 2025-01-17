package org.easy.configs

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureMultiplatformLibrary() {
  extensions.configure(KotlinMultiplatformExtension::class) {
//    explicitApi()
    androidTarget {
      compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
      }
    }

    listOf(
      iosX64(),
      iosArm64(),
      iosSimulatorArm64()
    ).forEach { iosTarget ->
      iosTarget.binaries.framework {
        baseName = "ComposeApp"
        isStatic = true
      }
    }
  }
}

internal fun Project.configureMultiplatformAndroid() {
  extensions.configure(KotlinMultiplatformExtension::class) {
    androidTarget {
      compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
      }
    }

    listOf(
      iosX64(),
      iosArm64(),
      iosSimulatorArm64()
    ).forEach { iosTarget ->
      iosTarget.binaries.framework {
        baseName = "ComposeApp"
        isStatic = true
      }
    }
  }
}
