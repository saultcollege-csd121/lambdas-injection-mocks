package dbExample.withInterface;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testReturnsEmptyListIfNoQuickRecipes() {

        // Here, we inject a 'mock' implementation of DataService that returns a list of recipes
        // when getRecipes() is called. (See the MockDB class below)
        var recipes = Main.getQuickRecipes(new MockDB());
        assertEquals(0, recipes.size());
    }

    @Test
    void testReturnsAllRecipesIfAllQuick() {

        // Since DataService happens to be a functional interface, we can use a lambda expression
        // to implement the getRecipes method. This is a quick way to create a 'mock' implementation
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 15),
                new Recipe(1, "", "", "", 4, 10, 10, 1),
                new Recipe(2, "", "", "", 4, 10, 10, 10)
        ));

        assertEquals(3, recipes.size());
    }

    @Test
    void testTypicalDB() {

        // We will verify that these two recipes are returned in the final list
        // so we get references to them here
        var qr1 = new Recipe(0, "", "", "", 4, 10, 10, 10);
        var qr2 = new Recipe(1, "", "", "", 4, 10, 10, 15);

        // In this case, we use an anonymous class to implement the DataService interface
        // Any of the approaches used in these tests can be used to create a 'mock' implementation
        // depending on the complexity of the test; for simple cases, a lambda expression is enough,
        // but sometimes an anonymous or concrete class is more appropriate.  For example, if you want to
        // reuse the 'mock' implementation in multiple tests, you should create a separate class for it.
        var recipes = Main.getQuickRecipes(new DataService() {
            @Override
            public List<Recipe> getRecipes() {
                return List.of(
                        qr1,
                        qr2,
                        new Recipe(1, "", "", "", 4, 10, 10, 16),
                        new Recipe(1, "", "", "", 4, 10, 10, 20),
                        new Recipe(2, "", "", "", 4, 10, 10, 2343)
                );
            }

        });

        assertEquals(2, recipes.size());

        // Verify that the two recipes we expected are in fact in the list
        assertTrue(recipes.contains(qr1));
        assertTrue(recipes.contains(qr2));
    }

    private static class MockDB implements DataService {
        @Override
        public List<Recipe> getRecipes() {
            return List.of(
                    new Recipe(0, "", "", "", 4, 10, 10, 20),
                    new Recipe(1, "", "", "", 4, 10, 10, 20),
                    new Recipe(2, "", "", "", 4, 10, 10, 20)
            );
        }
    }

}