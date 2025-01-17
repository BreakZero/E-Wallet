plugins {
  id("easy.multiplatform.library")
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {  }
    }
  }
}

android {
  namespace = "org.easy.wallet.model"
}