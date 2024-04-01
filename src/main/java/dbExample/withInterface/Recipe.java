package dbExample.withInterface;

import java.time.LocalDateTime;

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
