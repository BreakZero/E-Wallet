package org.easy.wallet.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

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

  }
}