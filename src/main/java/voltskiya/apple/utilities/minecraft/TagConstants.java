package voltskiya.apple.utilities.minecraft;

import org.bukkit.entity.Entity;

public class TagConstants {

    public static String IS_DOING_ABILITY = "isDoingAbility";
    public static final String NO_FALL_DAMAGE = "no_fall_damage";
    public static final String NO_SUFFOCATION_DAMAGE = "no_wall_damage";
    public static final String NO_BEE_STING_POISON = "sting_no_poison";
    public static final String NO_COLLIDE_FIREBALL_THROW = "no_collide_fireball_throw";
    public static final String NO_BEE_STING_POISON_INFLICTED = "no_poison_inflicted";

    public static void addIsDoingAbility(Entity entity) {
        entity.addScoreboardTag(IS_DOING_ABILITY);
    }

    public static void removeIsDoingAbility(Entity entity) {
        entity.removeScoreboardTag(IS_DOING_ABILITY);
    }
}
