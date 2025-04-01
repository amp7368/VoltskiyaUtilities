package voltskiya.apple.utilities;

import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EntityLocation {

    public final double xFacing;
    public final double yFacing;
    public final double zFacing;
    public final UUID uuid;
    public final double x;
    public final double y;
    public final double z;

    public EntityLocation(UUID uuid, double x, double y, double z, double xFacing, double yFacing, double zFacing) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xFacing = xFacing;
        this.yFacing = yFacing;
        this.zFacing = zFacing;
    }

    public EntityLocation(Entity e, double offsetX, double offsetY, double offsetZ) {
        this.uuid = e.getUniqueId();
        final Location location = e.getLocation();
        this.x = location.getX() + offsetX;
        this.y = location.getY() + offsetY;
        this.z = location.getZ() + offsetZ;
        this.xFacing = location.getDirection().getX();
        this.yFacing = location.getDirection().getY();
        this.zFacing = location.getDirection().getZ();
    }

    @Override
    public String toString() {
        return "<" + x + " , " + y + " , " + z + ">";
    }
}
