package voltskiya.apple.utilities.util.minecraft;

import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.bukkit.Material.*;

public class MaterialUtils {
    private final static Collection<Material> ARROWS = Arrays.asList(
            ARROW,
            SPECTRAL_ARROW,
            TIPPED_ARROW
    );
    private static final Collection<Material> TREE_WOOD = new HashSet<>(Arrays.asList(
            BIRCH_WOOD,
            ACACIA_WOOD,
            DARK_OAK_WOOD,
            JUNGLE_WOOD,
            SPRUCE_WOOD,
            OAK_WOOD,
            BIRCH_LOG,
            ACACIA_LOG,
            DARK_OAK_LOG,
            JUNGLE_LOG,
            SPRUCE_LOG,
            OAK_LOG,
            STRIPPED_BIRCH_WOOD,
            STRIPPED_ACACIA_WOOD,
            STRIPPED_DARK_OAK_WOOD,
            STRIPPED_JUNGLE_WOOD,
            STRIPPED_SPRUCE_WOOD,
            STRIPPED_OAK_WOOD,
            STRIPPED_BIRCH_LOG,
            STRIPPED_ACACIA_LOG,
            STRIPPED_DARK_OAK_LOG,
            STRIPPED_JUNGLE_LOG,
            STRIPPED_SPRUCE_LOG,
            STRIPPED_OAK_LOG
    ));
    private static final Collection<Material> LEAVES = Arrays.asList(BIRCH_WOOD,
            ACACIA_LEAVES,
            DARK_OAK_LEAVES,
            JUNGLE_LEAVES,
            SPRUCE_LEAVES,
            OAK_LEAVES
    );
    private static final Collection<Material> FENCE = List.of(
            BIRCH_FENCE,
            ACACIA_FENCE,
            DARK_OAK_FENCE,
            JUNGLE_FENCE,
            SPRUCE_FENCE,
            OAK_FENCE,
            WARPED_FENCE,
            CRIMSON_FENCE
    );
    private final static Collection<Material> BUTTONS = Arrays.asList(
            BIRCH_BUTTON,
            ACACIA_BUTTON,
            DARK_OAK_BUTTON,
            JUNGLE_BUTTON,
            SPRUCE_BUTTON,
            OAK_BUTTON,
            WARPED_BUTTON,
            CRIMSON_BUTTON,
            POLISHED_BLACKSTONE_BUTTON,
            STONE_BUTTON
    );

    private final static Collection<Material> TRAP_DOORS = Arrays.asList(
            BIRCH_TRAPDOOR,
            ACACIA_TRAPDOOR,
            DARK_OAK_TRAPDOOR,
            JUNGLE_TRAPDOOR,
            SPRUCE_TRAPDOOR,
            OAK_TRAPDOOR,
            WARPED_TRAPDOOR,
            CRIMSON_TRAPDOOR,
            IRON_TRAPDOOR
    );
    private final static Collection<Material> CARPETS = Arrays.asList(
            WHITE_CARPET,
            ORANGE_CARPET,
            MAGENTA_CARPET,
            LIGHT_BLUE_CARPET,
            YELLOW_CARPET,
            LIME_CARPET,
            PINK_CARPET,
            GRAY_CARPET,
            LIGHT_GRAY_CARPET,
            CYAN_CARPET,
            PURPLE_CARPET,
            BLUE_CARPET,
            BROWN_CARPET,
            GREEN_CARPET,
            RED_CARPET,
            BLACK_CARPET
    );
    private static final Collection<Material> walkThroughable = new HashSet<>(Arrays.asList(
            SNOW,
            GRASS,
            FERN,
            FLOWER_POT,
            TALL_GRASS,
            LARGE_FERN,
            REDSTONE,
            STRING,
            FIRE,
            RAIL,
            POWERED_RAIL,
            DETECTOR_RAIL,
            SOUL_FIRE
    )) {{
        addAll(TRAP_DOORS);
        addAll(BUTTONS);
        addAll(CARPETS);
    }};
    private static final Collection<Material> helmets = Arrays.asList(
            LEATHER_HELMET,
            GOLDEN_HELMET,
            CHAINMAIL_HELMET,
            IRON_HELMET,
            DIAMOND_HELMET,
            NETHERITE_HELMET,
            TURTLE_HELMET
    );
    private static final Collection<Material> chestplates = Arrays.asList(
            LEATHER_CHESTPLATE,
            GOLDEN_CHESTPLATE,
            CHAINMAIL_CHESTPLATE,
            IRON_CHESTPLATE,
            DIAMOND_CHESTPLATE,
            NETHERITE_CHESTPLATE
    );
    private static final Collection<Material> leggings = Arrays.asList(
            LEATHER_LEGGINGS,
            GOLDEN_LEGGINGS,
            CHAINMAIL_LEGGINGS,
            IRON_LEGGINGS,
            DIAMOND_LEGGINGS,
            NETHERITE_LEGGINGS
    );
    private static final Collection<Material> boots = Arrays.asList(
            LEATHER_BOOTS,
            GOLDEN_BOOTS,
            CHAINMAIL_BOOTS,
            IRON_BOOTS,
            DIAMOND_BOOTS,
            NETHERITE_BOOTS
    );
    private static final Collection<Material> armor = new HashSet<>() {{
        addAll(helmets);
        addAll(chestplates);
        addAll(leggings);
        addAll(boots);
    }};

    @Nullable
    public static ArmorType getArmorType(Material m) {
        if (armor.contains(m)) {
            if (helmets.contains(m)) return ArmorType.HELMET;
            if (chestplates.contains(m)) return ArmorType.CHESTPLATE;
            if (leggings.contains(m)) return ArmorType.LEGGINGS;
            if (boots.contains(m)) return ArmorType.BOOTS;
        }
        return null;
    }

    public static boolean isArrow(Material m) {
        return ARROWS.contains(m);
    }

    public static boolean isFence(Material m) {
        return FENCE.contains(m);
    }

    public static boolean isBowLike(Material m) {
        return BOW == m || CROSSBOW == m;
    }

    public static boolean isPassable(Material m) {
        return m.isAir() || m == SNOW;
    }

    public static boolean isWalkThroughable(Material m) {
        return m.isAir() || walkThroughable.contains(m);
    }

    public static boolean isTree(Material m) {
        return LEAVES.contains(m) || TREE_WOOD.contains(m);
    }

}
