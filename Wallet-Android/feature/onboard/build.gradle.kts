@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("org.easy.android.library.compose")
    id("org.easy.koin")
}

android {
    namespace = "com.easy.wallet.onboard"
}

dependencies {
    implementation(project(":Wallet-Android:design-system"))
    implementation(project(":Wallet-Android:core"))
    implementation(project(":platform:shared"))
    implementation(project(":platform:datastore"))
    implementation(project(":platform:model"))
}
