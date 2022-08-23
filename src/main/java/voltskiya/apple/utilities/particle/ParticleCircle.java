package voltskiya.apple.utilities.particle;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.chance.ChanceShapes;

import java.awt.geom.Point2D;

public class ParticleCircle implements ParticleManager {
    private final ChanceShapes random = new ChanceShapes();
    private Location center;

    public ParticleCircle(Location center) {
        this.center = center;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    public Location[] hollow(double density, double outerRadius, double height) {
        density *= 2 * Math.PI * outerRadius;
        return hollowWithCount((int) density, outerRadius, height);
    }

    public Location[] hollowWithCount(int count, double outerRadius, double height) {
        Location[] locations = new Location[count];
        for (int i = 0; i < count; i++) {
            Point2D.Double point = random.circle(outerRadius);
            locations[i] = locationFromPoint(point, height * random.random().nextDouble());
        }
        return locations;
    }

    public Location[] innerToOuter(double density, double outerRadius, double innerRadius, double height) {
        density *= Math.PI * (outerRadius * outerRadius - innerRadius * innerRadius);
        return innerToOuterWithCount((int) density, outerRadius, innerRadius, height);
    }

    public Location[] innerToOuterWithCount(int count, double outerRadius, double innerRadius, double height) {
        Location[] locations = new Location[count];
        for (int i = 0; i < count; i++) {
            Point2D.Double point = random.circle(innerRadius, outerRadius);
            locations[i] = locationFromPoint(point, height * random.random().nextDouble());
        }
        return locations;
    }

    @NotNull
    public Location locationFromPoint(Point2D.Double point, double y) {
        Location loc = center.clone();
        loc.add(point.x, y, point.y);
        loc.setX(point.x + loc.getX());
        loc.setZ(point.y + loc.getZ());
        loc.setY(y + loc.getY());
        return loc;
    }

    @NotNull
    public Location locationFromPoint(double x, double z) {
        Location loc = center.clone();
        loc.setX(x + loc.getX());
        loc.setZ(z + loc.getZ());
        return loc;
    }
}
