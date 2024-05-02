package pl.wincenciuk.imagesearcher.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import pl.wincenciuk.imagesearcher.presentation.SearcherViewModel
import pl.wincenciuk.imagesearcher.presentation.screens.MainScreen
import pl.wincenciuk.imagesearcher.presentation.screens.DetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = getViewModel<SearcherViewModel>()

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.name) {
        composable(AppScreens.MainScreen.name) {
            MainScreen(navController, viewModel)
        }
        composable(AppScreens.DetailsScreen.name) {
            DetailsScreen(viewModel)
        }
    }
}