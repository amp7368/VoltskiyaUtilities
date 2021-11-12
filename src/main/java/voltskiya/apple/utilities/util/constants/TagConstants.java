package voltskiya.apple.utilities.util.constants;

import org.bukkit.entity.Entity;

public class TagConstants {
    public static final String isDoingAbility = "isDoingAbility";

    public static void addIsDoingAbility(Entity entity) {
        entity.addScoreboardTag(isDoingAbility);
    }

    public static void removeIsDoingAbility(Entity entity) {
        entity.removeScoreboardTag(isDoingAbility);
    }
}
