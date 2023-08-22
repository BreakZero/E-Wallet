package com.easy.wallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.easy.wallet.discover.DiscoverScreen
import com.easy.wallet.home.navigation.homeNavigationRoute
import com.easy.wallet.home.navigation.homeScreen
import com.easy.wallet.marketplace.MarketplaceScreen
import com.easy.wallet.ui.WalletAppState

@Composable
fun WalletNavHost(
    appState: WalletAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen()
        composable(TopLevelDestination.MARKETPLACE.name) {
            MarketplaceScreen()
        }
        composable(TopLevelDestination.DISCOVER.name) {
            DiscoverScreen()
        }
    }
}