package lab7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Get all recipes that can be made in 15 minutes or less
     * @param dataService a service that provides access to recipe data
     * @return a list of quick recipes
     */
    public static List<Recipe> getQuickRecipes(DataService dataService) {
        try {
            var recipes = dataService.getRecipes();
            return recipes.stream().filter( r -> r.totalTime() <= 15 ).toList();
        } catch(Exception e ) {
            logger.error("Error while getting quick recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    // TODO: implement the searchRecipes method
    public static List<Recipe> searchRecipes(String search, DataService dataService) {
        try {
            var recipes = dataService.getRecipes();
            String searchLowerCase = search.toLowerCase();
            return recipes.stream().filter( r -> r.name().toLowerCase().contains(searchLowerCase) || r.description().toLowerCase().contains(searchLowerCase)).toList();
        } catch (DataService.DataException e) {
            logger.error("Error while searching recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    public static void main(String[] args) {
        // Here, we INJECT a concrete implementation of the DataService interface
        // that allows us to get data from an SQLite database
        var dataService = new SqliteDataService();

        var quickRecipes = getQuickRecipes(new SqliteDataService());
        System.out.println("Quick Recipes:");
        quickRecipes.forEach(System.out::println);

        // TODO: use your searchRecipes method with a SqliteDataService object
        String searchTerm = "chicken";
        var foundRecipes = searchRecipes(searchTerm, dataService);
        System.out.println("\nRecipes containing '" + searchTerm + "':");
        foundRecipes.forEach(System.out::println);
    }
}
