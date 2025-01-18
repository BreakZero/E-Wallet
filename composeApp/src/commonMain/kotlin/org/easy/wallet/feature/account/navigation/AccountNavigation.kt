package org.easy.wallet.feature.account.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable
import org.easy.wallet.feature.account.AccountScreen

@Serializable
data object AccountBaseRoute

@Serializable
data object AccountRoute

fun NavController.navigateToAccount(navOptions: NavOptions) = navigate(route = AccountRoute, navOptions)

fun NavGraphBuilder.accountSection(accountDestination: NavGraphBuilder.() -> Unit,) {
  navigation<AccountBaseRoute>(startDestination = AccountRoute) {
    composable<AccountRoute> {
      AccountScreen()
    }
    accountDestination()
  }
}