package org.easy.wallet.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.easy.wallet.feature.account.navigation.accountSection
import org.easy.wallet.feature.apps.navigation.appsSection
import org.easy.wallet.feature.news.navigation.newsSection

@Composable
fun WalletNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  startDestination: Any,
) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = startDestination
  ) {
    newsSection { }
    appsSection { }
    accountSection { }
  }
}