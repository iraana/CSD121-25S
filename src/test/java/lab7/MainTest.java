package lab7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoData() {
        var recipes = Main.getQuickRecipes(List::of);
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoQuickRecipes() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 16),
                new Recipe(1, "", "", "", 4, 10, 10, 20),
                new Recipe(2, "", "", "", 4, 10, 10, 200)
        ));
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsAllRecipesIfAllQuick() {

        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 15),
                new Recipe(1, "", "", "", 4, 10, 10, 1),
                new Recipe(2, "", "", "", 4, 10, 10, 10)
        ));

        assertEquals(3, recipes.size());
    }

    @Test
    void testGetQuickRecipesWorksOnTypicalData() {

        var recipes = Main.getQuickRecipes(() -> List.of(
                        new Recipe(0, "", "", "", 4, 10, 10, 10),
                        new Recipe(1, "", "", "", 4, 10, 10, 15),
                        new Recipe(2, "", "", "", 4, 10, 10, 16),
                        new Recipe(3, "", "", "", 4, 10, 10, 20),
                        new Recipe(4, "", "", "", 4, 10, 10, 2343)
        ));

        assertEquals(2, recipes.size());

        // Verify that the two recipes we expected are in fact in the list
        assertEquals(0, recipes.get(0).id());
        assertEquals(1, recipes.get(1).id());
    }

    // TODO: test the searchRecipes method
    @Test
    void testSearchRecipesReturnsEmptyListIfNoMatch() {
        var recipes = Main.searchRecipes("butter", () -> List.of(
                new Recipe(0, "Chicken Soup", "Chicken broth", "......", 4, 10, 10, 30),
                new Recipe(1, "Beef Stew", "Rich stew with vegetables", ".....", 4, 10, 10, 60)
        ));
        assertEquals(0, recipes.size());
    }

    @Test
    void testSearchRecipesFindsRecipeByName() {
        var recipes = Main.searchRecipes("soup", () -> List.of(
                new Recipe(0, "Chicken Soup", "Chicken broth", "......", 4, 10, 10, 30),
                new Recipe(1, "Beef Stew", "Rich stew with vegetables", ".....", 4, 10, 10, 60)
        ));
        assertEquals(1, recipes.size());
        assertEquals(0, recipes.get(0).id());
    }

    @Test
    void testSearchRecipesFindsRecipeByDescription() {
        var recipes = Main.searchRecipes("vegetables", () -> List.of(
                new Recipe(0, "Chicken Soup", "Chicken broth", "......", 4, 10, 10, 30),
                new Recipe(1, "Beef Stew", "Rich stew with vegetables", ".....", 4, 10, 10, 60)
        ));
        assertEquals(1, recipes.size());
        assertEquals(1, recipes.get(0).id());
    }

    @Test
    void testSearchRecipesIsCaseInsensitive() {
        var recipes = Main.searchRecipes("chicken", () -> List.of(
                new Recipe(0, "ChICKEN Soup", "Chicken broth", "......", 4, 10, 10, 30),
                new Recipe(1, "Stew", "Rich CHICKEN stew with vegetables", ".....", 4, 10, 10, 60)
        ));
        assertEquals(2, recipes.size());
        assertEquals(0, recipes.get(0).id());
        assertEquals(1, recipes.get(1).id());
    }
}