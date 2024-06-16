package th.learn.therecipe

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController,innerPadding: PaddingValues){
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable( route = Screen.RecipeScreen.route ) {
            RecipeScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                viewState = viewState,
                navigateToDetails =
             {
                navController.currentBackStackEntry?.savedStateHandle?.set("category",it)
                navController.navigate(Screen.RecipeDetailScreen.route)
            })
        }
        composable (route = Screen.RecipeDetailScreen.route) {
            val category = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Category>("category")
                ?: Category(
                    idCategory = "",
                    strCategory = "",
                    strCategoryThumb = "",
                    strCategoryDescription = ""
                )
            
            CategoryDetailScreen(category = category,innerPadding)
        }

    }
}
