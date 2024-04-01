package dbExample.withInterface;

import java.sql.SQLException;
import java.util.List;

public interface DataService {

    /**
     * Get all recipes from the database
     * @return a list of recipes
     */
    List<Recipe> getRecipes();
}
