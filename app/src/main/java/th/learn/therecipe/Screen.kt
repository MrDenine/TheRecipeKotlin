package th.learn.therecipe

sealed class Screen (val route:String) {
    data object RecipeScreen:Screen("recipeScreen")
    data object RecipeDetailScreen:Screen("recipeDetailScreen")
}