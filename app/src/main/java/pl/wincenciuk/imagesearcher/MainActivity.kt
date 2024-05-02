package pl.wincenciuk.imagesearcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pl.wincenciuk.imagesearcher.presentation.navigation.AppNavigation
import pl.wincenciuk.imagesearcher.ui.theme.ImageSearcherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearcherTheme {
                AppNavigation()
            }
        }
    }
}
