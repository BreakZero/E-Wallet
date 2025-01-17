package ai.askquin.feature.reading.navigation

import org.easy.wallet.feature.news.NewsScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.serialization.Serializable

@Serializable
data object ReadingBaseRoute

@Serializable
data object ReadingRoute

fun NavController.navigateToReading(navOptions: NavOptions) = navigate(route = ReadingRoute, navOptions)

fun NavGraphBuilder.readingSection(readingDestination: NavGraphBuilder.() -> Unit,) {
  navigation<ReadingBaseRoute>(startDestination = ReadingRoute) {
    composable<ReadingRoute> {
      NewsScreen()
    }
    readingDestination()
  }
}