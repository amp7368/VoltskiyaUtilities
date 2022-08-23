package voltskiya.apple.utilities.trash;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.UtilitiesVoltskiyaPlugin;

import java.util.List;

public class OldMultiBlockListener implements Listener {

    public OldMultiBlockListener() {
        Bukkit.getPluginManager().registerEvents(this, UtilitiesVoltskiyaPlugin.get());
    }

    public synchronized static void addBlockPlacement(OldMultiBlockType multiBlock) {
//        multiBlocks.put(multiBlock.getItemKey(), multiBlock);
    }

    public synchronized static void addBlockBreaking(OldMultiBlockType multiBlock, List<Location> blocks) {
        OldMutliBlockStructure structure = new OldMutliBlockStructure(blocks, multiBlock);
        for (Location block : blocks) {
//            placedMultiBlocks.put(block, structure);
        }
    }

    /**
     * @return true if this was a multiblock broken
     */
    private synchronized ItemStack breakBlock(Material oldMaterial, Material newMaterial, int x, int y, int z, World world, Cancellable event) {
//        OldMutliBlockStructure multiblock = placedMultiBlocks.remove(world.getUID(), x, y, z);
//        if (multiblock != null) {
//            for (Location location : multiblock.blocks()) {
//                placedMultiBlocks.remove(location);
//            }
//            return multiblock.multiBlock().getItemWithKey();
//        }
        return null;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockDestroyEvent event) {
        @NotNull Block blockBroken = event.getBlock();
        ItemStack itemDropped = breakBlock(
                blockBroken.getType(),
                event.getNewState().getMaterial(),
                blockBroken.getX(),
                blockBroken.getY(),
                blockBroken.getZ(),
                blockBroken.getWorld(),
                event
        );
        if (itemDropped != null) {
            World world = blockBroken.getWorld();
            Location location = blockBroken.getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(UtilitiesVoltskiyaPlugin.get(), () -> world.dropItem(location, itemDropped), 1);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        @NotNull Block blockBroken = event.getBlock();
        ItemStack itemDropped = breakBlock(
                blockBroken.getType(),
                null,
                blockBroken.getX(),
                blockBroken.getY(),
                blockBroken.getZ(),
                blockBroken.getWorld(),
                event
        );
        if (itemDropped != null) {
            event.setDropItems(false);
            World world = blockBroken.getWorld();
            Location location = blockBroken.getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(UtilitiesVoltskiyaPlugin.get(), () -> world.dropItem(location, itemDropped), 1);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(EntityExplodeEvent event) {
        @NotNull List<Block> blockBroken = event.blockList();
        for (Block block : blockBroken) {
            ItemStack itemDropped = breakBlock(
                    block.getType(),
                    null,
                    block.getX(),
                    block.getY(),
                    block.getZ(),
                    block.getWorld(),
                    event);
            if (itemDropped != null) {
                event.setYield(0f);
                World world = block.getWorld();
                Location location = block.getLocation();
                Bukkit.getScheduler().scheduleSyncDelayedTask(UtilitiesVoltskiyaPlugin.get(), () -> world.dropItem(location, itemDropped), 1);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public synchronized void place(BlockPlaceEvent event) {
        @NotNull ItemStack item = event.getItemInHand();
        ItemMeta itemMeta = item.getItemMeta();
        for (NamespacedKey key : itemMeta.getPersistentDataContainer().getKeys()) {
//            if (multiBlocks.containsKey(key)) {
//                multiBlocks.get(key).place(event.getBlock().getLocation(), event.getPlayer().getLocation().getYaw());
//            }
        }
    }
}
