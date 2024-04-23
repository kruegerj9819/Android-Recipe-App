package com.example.chefturnersguidetocooking

//import com.example.chefturnersguidetocooking.ui.theme.ChefTurnersGuideToCookingTheme
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chefturnersguidetocooking.database.DatabaseRepository
import com.example.chefturnersguidetocooking.database.DatabaseViewModel
import com.example.chefturnersguidetocooking.database.RecipeDatabase
import com.example.chefturnersguidetocooking.ui.theme.RecipeTheme

/**
 * Activity for Recipes app
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class) // ? This does not exist
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            RecipeAppContent()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun RecipeAppContent() {
        RecipeTheme {
            // val layoutDirection = LocalLayoutDirection.current // Not sure what this is for, never used
            val navController = rememberNavController()

            Scaffold(
                modifier = Modifier.fillMaxSize(), // Ensure the Scaffold fills the entire screen
                content = {
                    val onBackPressed: () -> Unit = {
                        // Define your logic for back button press here
                    }
                    val database = RecipeDatabase.getInstance(applicationContext)
                    val repository = DatabaseRepository.getRepository(database)
                    val dbViewModel = DatabaseViewModel(repository)
                    val windowSize: WindowWidthSizeClass =
                        WindowWidthSizeClass.Compact // Define your logic for windowSize here
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            RecipeApp(
                                windowSize = windowSize, // Define your logic for windowSize here
                                onBackPressed = onBackPressed,
                                navController = navController,
                                dbViewModel = dbViewModel
                            )
                        }
                        composable("add_recipes") { AddingView() }
                        composable("favorites") { FavoritesView() }
                    }
                },
                bottomBar = {
                    BottomNavigation(navController = navController)
                }
            )
        }
    }
}