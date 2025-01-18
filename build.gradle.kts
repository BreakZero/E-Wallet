plugins {
  // this is necessary to avoid the plugins to be loaded multiple times
  // in each subproject's classloader
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.composeMultiplatform) apply false
  alias(libs.plugins.composeCompiler) apply false
  alias(libs.plugins.kotlinMultiplatform) apply false
  alias(libs.plugins.kotlinAndroid) apply false
  alias(libs.plugins.ktlint) apply false
}

subprojects {
  apply(plugin = "org.jlleitschuh.gradle.ktlint")

  configure<org.jlleitschuh.gradle.ktlint.KtlintExtension>() {
    version.set("1.4.0")
    filter {
      include("**/kotlin/**")
//      exclude("**/generated/**")
      exclude { element ->
        val path = element.file.path
        path.contains("\\generated\\") || path.contains("/generated/")
      }
      exclude("**.kts")
    }
  }
}