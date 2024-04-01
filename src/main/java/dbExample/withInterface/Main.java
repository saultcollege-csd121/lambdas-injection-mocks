package dbExample.withInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(dbExample.noInterface.Main.class);

    public static List<Recipe> getQuickRecipes(DataService dataService) {
        // We are now depending on an interface that provides the methods this function needs in order to work
        // namely the getRecipes method.
        // This allows the main function below to INJECT a concrete implementation (SqliteDataService)
        // but it ALSO allows are tests to inject MOCK implementations to set up data scenarios specific to
        // each test (see test/java/dbExample/withInterface/MainTest.java)
        try {
            var recipes = dataService.getRecipes();
            return recipes.stream().filter( r -> r.totalTime() <= 15 ).toList();
        } catch(Exception e ) {
            logger.error("Error while getting recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    public static void main(String[] args) {
        // Here, we INJECT a concrete implementation of the DataService interface
        // that allows us to get data from an SQLite database
        var quickRecipes = getQuickRecipes(new SqliteDataService());
        System.out.println("Quick Recipes:");
        quickRecipes.forEach(System.out::println);
    }
}
