package voltskiya.apple.utilities.minecraft;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import voltskiya.apple.utilities.DistanceUtils;
import voltskiya.apple.utilities.PluginUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class EntityUtils {
    private static final HashSet<EntityType> hostiles = new HashSet<>();

    static {
        File file = new File(PluginUtils.get().getDataFolder(), "hostileMobs.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (!config.contains("hostiles")) {
            config.set("hostiles", Collections.emptyList());
            try {
                config.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<String> hostilesNames = config.getStringList("hostiles");
        for (
                String hostile : hostilesNames) {
            try {
                hostiles.add(EntityType.valueOf(hostile));
            } catch (IllegalArgumentException e) {
                PluginUtils.get().log(Level.WARNING, hostile + " is not a valid entityType in " + file.getPath());
            }
        }

    }

    public static boolean isHostile(LivingEntity alive) {
        return alive != null && (alive instanceof Monster || hostiles.contains(alive.getType()));
    }

    public static List<Entity> sortByClosest(Collection<Entity> entities, Location center) {
        return entities.stream()
                .sorted(Comparator.comparingDouble(e -> DistanceUtils.distance(center, e.getLocation())))
                .collect(Collectors.toList());
    }
}
