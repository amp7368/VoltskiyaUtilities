package voltskiya.apple.utilities.util;

import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voltskiya.apple.utilities.util.data_structures.XYZ;

public class VectorUtils {
    public static double yaw(Vector vector) {
        return yaw(vector.getX(), vector.getZ());
    }

    public static double yaw(double x, double z) {
        return Math.atan2(z, x) * 180;
    }

    @NotNull
    public static Vector rotateVector(double x1, double z1, double x2, double z2, double y, double theta) {
        double x2Old = x1 + x2;
        double z2Old = z1 + z2;
        // rotate these two points
        double x1New = x1 * Math.cos(theta) - z1 * Math.sin(theta);
        double z1New = z1 * Math.cos(theta) + x1 * Math.sin(theta);
        double x2New = x2Old * Math.cos(theta) - z2Old * Math.sin(theta);
        double z2New = z2Old * Math.cos(theta) + x2Old * Math.sin(theta);
        return new Vector(x2New - x1New, y, z2New - z1New);
    }

    @NotNull
    public static Vector rotateVector(double facingX, double facingZ, double facingY, double rotation) {
        double angleStarting = Math.atan2(facingZ, facingX);
        angleStarting += rotation;
        return new Vector(Math.cos(angleStarting), facingY, Math.sin(angleStarting));
    }

    /**
     * rotates the entity about a center
     *
     * @param entityLocation the location to rotate
     * @param yaw            the new direction for entityLocation to face
     * @param center         the center to rotate about
     * @param isModifyEntity whether to modify the specified entity's location
     * @return the new rotated loaction
     */
    @NotNull
    public static Location rotate(EntityLocation entityLocation, float yaw, Location center, boolean isModifyEntity) {
        Location l = new Location(null, 0, 0, 0, yaw % 360, 0);
        return rotate(entityLocation, l.getDirection(), center, isModifyEntity);
    }

    /**
     * rotates the entity about a center
     *
     * @param entityLocation the location to rotate
     * @param newFacing      the new direction for entityLocation to face
     * @param center         the center to rotate about
     * @param isModifyEntity whether to modify the specified entity's location
     * @return the new rotated loaction
     */
    public static @NotNull Location rotate(EntityLocation entityLocation, Vector newFacing, Location center, boolean isModifyEntity) {

        Location newLocation = rotate(entityLocation.x, entityLocation.y, entityLocation.z,
                entityLocation.xFacing, entityLocation.zFacing,
                center.getX(), center.getY(), center.getZ(),
                center.getDirection().getX(), center.getDirection().getZ());

        if (isModifyEntity) {
            @Nullable Entity entity = Bukkit.getEntity(entityLocation.uuid);
            if (entity != null) {
                Location changeLocation = entity.getLocation().setDirection(newLocation.getDirection());
                changeLocation.setX(newLocation.getX());
                changeLocation.setZ(newLocation.getY());
                entity.teleport(changeLocation);
            }
        }

        return newLocation;
    }

    public static Location rotate(XYZ<Double> relPos, XYZ<Double> relFacing,
                                  XYZ<Double> center, double rotation) {
        return rotate(
                relPos.getX(), relPos.getY(), relPos.getZ(),
                relFacing.getX(), relFacing.getZ(),
                center.getX(), center.getY(), center.getZ(),
                Math.cos(rotation), Math.sin(rotation)
        );
    }

    public static Location rotate(double posX, double posY, double posZ,
                                  double facingX, double facingZ,
                                  double centerX, double centerY, double centerZ,
                                  double angleX, double angleZ) {
        double radius = DistanceUtils.magnitude(posX, 0, posZ);

        // do the position rotation
        double angle = Math.atan2(posZ, posX);
        angle += Math.atan2(angleZ, angleX);
        double x = Math.cos(angle) * radius + centerX;
        double z = Math.sin(angle) * radius + centerZ;
        double y = posY + centerY;

        // do the facing rotation
        double theta = Math.atan2(angleZ, angleZ);
        while (theta < 0) theta += Math.PI * 2;
        Vector newEntityFacing = rotateVector(posX, posZ, facingX, facingZ, y, theta);
        Location newLocation = new Location(null, x, y, z);
        newLocation.setDirection(newEntityFacing);
        return newLocation;
    }

    public static double magnitude(Vector velocity) {
        double x = velocity.getX();
        double y = velocity.getY();
        double z = velocity.getZ();
        return magnitude(x, y, z);
    }

    public static double magnitude(double x, double y, double z) {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public static double magnitude(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public static double dot(double a1, double a2, double b1, double b2) {
        return a1 * b1 + a2 * b2;
    }

    public static double dot(double a1, double a2, double a3, double b1, double b2, double b3) {
        return a1 * b1 + a2 * b2 + a3 * b3;
    }

    @Nullable
    public static Location getHitLocation(@NotNull Location location, @NotNull Vector direction, int maxDistance, FluidCollisionMode fluidCollision, boolean ignorePassable) {
        @Nullable RayTraceResult raytrace = location.getWorld().rayTraceBlocks(location, direction, maxDistance, fluidCollision, ignorePassable);
        if (raytrace == null) return null;
        @Nullable Block hitBlock = raytrace.getHitBlock();
        if (hitBlock == null) return null;
        return hitBlock.getLocation();
    }

    @Nullable
    public static Location getHitLocation(@NotNull Location locationAndDirection, int maxDistance, FluidCollisionMode fluidCollision, boolean ignorePassable) {
        return getHitLocation(locationAndDirection, locationAndDirection.getDirection(), maxDistance, fluidCollision, ignorePassable);
    }
}
