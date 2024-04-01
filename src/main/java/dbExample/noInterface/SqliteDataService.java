package dbExample.noInterface;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteDataService {

    public SqliteDataService() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
    }

    public List<Recipe> getRecipes() throws SQLException {

        var connection = DriverManager.getConnection("jdbc:sqlite:recipes.sqlite");
        var stmt = connection.createStatement();
        var results = stmt.executeQuery("SELECT * FROM Recipes");
        var recipeNames = new ArrayList<Recipe>();
        while (results.next()) {
            recipeNames.add(new Recipe(
                    results.getLong("id"),
                    results.getString("name"),
                    results.getString("description"),
                    results.getString("instructions"),
                    results.getInt("servings"),
                    results.getInt("prep_time"),
                    results.getInt("cook_time"),
                    results.getInt("total_time")));
        }
        return recipeNames;
    }
}
