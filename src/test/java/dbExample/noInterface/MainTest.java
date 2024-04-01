package dbExample.noInterface;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testGetQuickRecipes() {
        // We can call the method here
        var recipes = Main.getQuickRecipes();

        // And we can perform some basic assertions on the resulting list
        assertNotNull(recipes);
        assertTrue(recipes.stream().allMatch(recipe -> recipe.totalTime() <= 15));
        // But this is not a good complete set of tests
        // For example, we should test that a database that has no quick recipes returns an empty list
        // We should also test that a database that has only quick recipes returns all of them
        // And we should test that a database with a mix of quick and slow recipes returns only the quick ones
        // BUT we have no control over the database that Main uses
        // The best we could do is write separate tests for each of these cases
        // then run them individually, manually changing the database for each test to one that fits the case.
        // This is not ideal, as it requires manual intervention and is error-prone.
    }

}