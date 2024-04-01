package dbExample.noInterface;

public record Recipe(
        long id,
        String name,
        String description,
        String instructions,
        int servings,
        int prepTime,
        int cookTime,
        int totalTime
) {
}
