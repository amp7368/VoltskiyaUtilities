package voltskiya.apple.utilities.particle;

import java.util.function.BiConsumer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public interface ParticleManager {

    default void particles(Location[] locations, BiConsumer<World, Location> particleCreator) {
        for (Location loc : locations) {
            particleCreator.accept(loc.getWorld(), loc);
        }
    }

    default void particles(Location[] locations, Particle particle) {
        for (Location loc : locations) {
            loc.getWorld().spawnParticle(particle, loc, 0, 0, 0, 0);
        }
    }
}
