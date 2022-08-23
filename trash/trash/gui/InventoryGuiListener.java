package voltskiya.apple.utilities.trash.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import voltskiya.apple.utilities.UtilitiesVoltskiyaPlugin;

public class InventoryGuiListener implements Listener {
    public InventoryGuiListener() {
        Bukkit.getPluginManager().registerEvents(this, UtilitiesVoltskiyaPlugin.get());
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryTurretGuiClick(InventoryClickEvent event) {
        final Inventory clickedInventory = event.getClickedInventory();
        if ((clickedInventory != null && clickedInventory.getHolder() instanceof OnGuiInventoryEvent gui)) {
            // clicking from the turret
            gui.onGuiInventory(event);
        } else {
            final InventoryHolder topInventory = event.getView().getTopInventory().getHolder();
            if (topInventory instanceof OnGuiInventoryEvent gui) {
                gui.onPlayerInventory(event);
            }
        }
    }
}