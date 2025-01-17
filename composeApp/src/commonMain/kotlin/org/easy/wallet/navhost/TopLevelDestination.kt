package org.easy.wallet.navhost

import easywallet.composeapp.generated.resources.Res
import easywallet.composeapp.generated.resources.compose_multiplatform
import easywallet.composeapp.generated.resources.tab_account
import easywallet.composeapp.generated.resources.tab_dapp
import easywallet.composeapp.generated.resources.tab_news
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import kotlin.reflect.KClass

enum class TopLevelDestination(
  val selectedIcon: DrawableResource,
  val unselectedIcon: DrawableResource,
  val titleTextId: StringResource,
  val route: KClass<*>,
  val baseRoute: KClass<*> = route,
) {
  News(
    selectedIcon = Res.drawable.compose_multiplatform,
    unselectedIcon = Res.drawable.compose_multiplatform,
    titleTextId = Res.string.tab_news,
    route = Any::class,
    baseRoute = Any::class
  ),
  DApps(
    selectedIcon = Res.drawable.compose_multiplatform,
    unselectedIcon = Res.drawable.compose_multiplatform,
    titleTextId = Res.string.tab_dapp,
    route = Any::class
  ),
  Account(
    selectedIcon = Res.drawable.compose_multiplatform,
    unselectedIcon = Res.drawable.compose_multiplatform,
    titleTextId = Res.string.tab_account,
    route = Any::class
  ),
}