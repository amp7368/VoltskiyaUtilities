package voltskiya.apple.utilities.minecraft.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.bukkit.util.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BlockCollideUtils {

    public static Vector checkBlockCollide(BoundingBox bigBox, World world) {
        Vector oldCenter = bigBox.getCenter();
        int x = oldCenter.getBlockX();
        int y = oldCenter.getBlockY();
        int z = oldCenter.getBlockZ();
        bigBox = bigBox.shift(oldCenter.multiply(-1));
        Vector min = bigBox.getMin();
        Vector max = bigBox.getMax();
        BoundingBox main = null;
        for (int xi = (int) min.getX() - 1; xi < max.getX() + 2; xi++) {
            for (int yi = (int) min.getY() - 1; yi < max.getY() + 2; yi++) {
                for (int zi = (int) min.getZ() - 1; zi < max.getZ() + 2; zi++) {
                    Block worldBlock = new Location(world, xi + x, yi + y, zi + z).getBlock();
                    if (!worldBlock.getType().isCollidable()) continue;
                    @NotNull VoxelShape block = worldBlock.getCollisionShape();
                    for (BoundingBox blockBox : block.getBoundingBoxes()) {
                        blockBox = blockBox.shift(xi, yi, zi);
                        if (!blockBox.overlaps(bigBox)) continue;
                        BoundingBox intersection = blockBox.intersection(bigBox);
                        if (intersection.getVolume() == 0) continue;
                        if (main == null) main = intersection;
                        else main.union(intersection);
                    }
                }
            }
        }
        if (main == null) return null;
        Vector normal = bigBox.getCenter().subtract(main.getCenter());
        if (normal.length() == 0) return null;
        return normal.normalize();
    }
}
