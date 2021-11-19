package voltskiya.apple.utilities.util.trash;

import java.util.Objects;
import java.util.UUID;

public class LocationCoords {
    private UUID world;
    private int x;
    private int y;
    private int z;

    public LocationCoords(UUID world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public UUID getWorld() {
        return world;
    }

    public void setWorld(UUID world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LocationCoords that) {
            return Objects.equals(this.world, that.world) &&
                    this.x == that.x &&
                    this.y == that.y &&
                    this.z == that.z;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) ((world.hashCode() + (long) x + y + z) % Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        return "LocationCoords[" +
                "world=" + world + ", " +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "z=" + z + ']';
    }

}
