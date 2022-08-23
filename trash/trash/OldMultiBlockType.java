package voltskiya.apple.utilities.trash;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public interface OldMultiBlockType {
    /**
     * call this to register it as a placeable multiblock
     */
    default void register() {
        OldMultiBlockListener.addBlockPlacement(this);
    }


    Map<Triple<Integer, Integer, Integer>, BlockData> getStructure();

    void createMultiBlockData(Location location, List<Location> blocksPlaced, float yaw);

    NamespacedKey getItemKey();

    ItemStack getItem();

    default ItemStack getItemWithKey() {
        ItemStack item = this.getItem();
        ItemMeta im = item.getItemMeta();
        im.getPersistentDataContainer().set(this.getItemKey(), PersistentDataType.STRING, "");
        item.setItemMeta(im);
        return item;
    }


    default void place(Location location, float yaw) {
        Map<Triple<Integer, Integer, Integer>, BlockData> structure = new HashMap<>(getStructure());
        List<Location> blocksPlaced = new ArrayList<>();
        UUID worldUUID = location.getWorld().getUID();
        for (Map.Entry<Triple<Integer, Integer, Integer>, BlockData> block : structure.entrySet()) {
            Triple<Integer, Integer, Integer> xyz = block.getKey();
            xyz = FacingUtils.rotate(xyz, yaw);
            location.getWorld().getBlockAt(
                    xyz.getX() + location.getBlockX(),
                    xyz.getY() + location.getBlockY(),
                    xyz.getZ() + location.getBlockZ()
            ).setBlockData(block.getValue());
            blocksPlaced.add(new Location(Bukkit.getWorld(worldUUID),
                    xyz.getX() + location.getBlockX(),
                    xyz.getY() + location.getBlockY(),
                    xyz.getZ() + location.getBlockZ()
            ));
        }
        createMultiBlockData(location, blocksPlaced, yaw);
        OldMultiBlockListener.addBlockBreaking(this, blocksPlaced);
    }
}
