package voltskiya.apple.utilities.trash.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class InventoryGui implements InventoryHolder, OnGuiInventoryEvent {
    protected final List<InventoryGuiPage> pageMap = new ArrayList<>();

    protected int page = 0;
    private InventoryGuiPage tempPage = null;

    protected void addPage(InventoryGuiPage... pageGuis) {
        for (InventoryGuiPage pageGui : pageGuis) {
            pageGui.fillInventory();
            pageMap.add(pageGui);
        }
    }

    public void nextPage(int count) {
        List<HumanEntity> viewers = getInventory().getViewers();
        page += count;
        page = Math.max(0, page);
        page = Math.min(pageMap.size() - 1, page);
        update(viewers);
    }

    public void setTempInventory(@Nullable InventoryGuiPage page) {
        List<HumanEntity> viewers = this.getInventory().getViewers();
        this.tempPage = page;
        update(viewers);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return getPage().getInventory();
    }

    public void update(@Nullable List<HumanEntity> viewers) {
        final InventoryGuiPage inventoryGuiPage = getPage();
        inventoryGuiPage.update();
        for (HumanEntity viewer : new ArrayList<>(viewers == null ? this.getInventory().getViewers() : viewers)) {
            viewer.openInventory(inventoryGuiPage.getInventory());
        }
    }

    protected InventoryGuiPage getPage() {
        return this.tempPage == null ? this.pageMap.get(page) : this.tempPage;
    }

    @Override
    public void onGuiInventory(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory != null)
            if (clickedInventory.getHolder() instanceof InventoryGui) {
                InventoryGuiPage p = getPage();
                if (p != null) {
                    event.setCancelled(true);
                    p.dealWithClick(event);
                }
            }
    }

    @Override
    public void onPlayerInventory(InventoryClickEvent event) {
        InventoryGuiPage p = getPage();
        if (p != null) {
            event.setCancelled(true);
            p.dealWithPlayerInventoryClick(event);
        }
        event.setCancelled(true);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void initialize2() {
    }

    @Override
    public void finalizePageItems() {
    }

    @Override
    public void showPageItems(@Nullable List<HumanEntity> viewers) {
    }

    public interface InventoryGuiPage extends InventoryHolder {
        String getName();

        @NotNull Inventory getInventory();

        void fillInventory();

        default void update() {
            this.fillInventory();
        }

        void dealWithClick(InventoryClickEvent event);

        default void dealWithPlayerInventoryClick(InventoryClickEvent event) {
        }

        int size();

    }

    public interface InventoryGuiSlot {
        void dealWithClick(InventoryClickEvent event);

        ItemStack getItem();
    }

}
