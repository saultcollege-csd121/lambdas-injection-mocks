package dbExample.noInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * @return a list of quick (<15 min) recipes
     */
    public static List<Recipe> getQuickRecipes() {
        try {
            // If we want to test getQuickRecipes as implemented here, we are necessarily going to need to
            // connect to the database that SqliteDataService connects to.
            // That really constrains the kinds of tests we can easily perform
            // (see test/java/dbExample/noInterface/MainTest.java)
            // Instead, let's INJECT this dependency and depend on a more general ABSTRACTION using an interface
            // (see main/java/dbExample/withInterface/*)
            var dataService = new SqliteDataService();
            var recipes = dataService.getRecipes();
            return recipes.stream().filter( r -> r.totalTime() <= 15 ).toList();
        } catch(Exception e ) {
            logger.error("Error while getting recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    public static void main(String[] args) {

        var quickRecipes = getQuickRecipes();
        System.out.println("Quick Recipes:");
        quickRecipes.forEach(System.out::println);
    }
}
