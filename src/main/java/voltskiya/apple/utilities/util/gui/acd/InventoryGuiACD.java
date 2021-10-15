package voltskiya.apple.utilities.util.gui.acd;

import apple.utilities.util.NumberUtils;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voltskiya.apple.utilities.util.gui.OnGuiInventoryEvent;
import voltskiya.apple.utilities.util.gui.acd.page.GuiPageable;

import java.util.ArrayList;
import java.util.List;

public class InventoryGuiACD implements OnGuiInventoryEvent, GuiPageable {
    protected final List<OnGuiInventoryEvent> pageMap = new ArrayList<>();
    private final List<OnGuiInventoryEvent> subPages = new ArrayList<>();

    protected int page = 0;

    public InventoryGuiACD() {

    }

    protected OnGuiInventoryEvent getPage() {
        return this.subPages.isEmpty() ? pageMap.get(page) : subPages.get(0);
    }

    protected void addPage(OnGuiInventoryEvent... pageGuis) {
        for (OnGuiInventoryEvent pageGui : pageGuis) {
            pageGui.initAll();
            pageMap.add(pageGui);
        }
    }

    @Override
    public void parentAddSubPage(OnGuiInventoryEvent subPage) {
        List<HumanEntity> viewers = getInventory().getViewers();
        subPage.initAll();
        this.subPages.add(0, subPage);
        refresh(viewers);
    }

    @Override
    public void parentRemoveSubPage() {
        List<HumanEntity> viewers = getInventory().getViewers();
        if (!subPages.isEmpty())
            this.subPages.remove(0);
        refresh(viewers);
    }

    @Override
    public void parentNext(int count) {
        List<HumanEntity> viewers = getInventory().getViewers();
        page = NumberUtils.getBetween(0, page + count, pageMap.size());
        refresh(viewers);
    }

    @Override
    public void parentRefresh() {
        refresh();
    }

    @Override
    public void finalizePageItems() {
        getPage().finalizePageItems();
    }

    @Override
    public void refreshPageItems() {
        getPage().refreshPageItems();
    }

    @Override
    public void showPageItems(@Nullable List<HumanEntity> viewers) {
        final OnGuiInventoryEvent inventoryGuiPage = getPage();
        for (HumanEntity viewer : new ArrayList<>(viewers == null ? getInventory().getViewers() : viewers)) {
            viewer.openInventory(inventoryGuiPage.getInventory());
        }
    }

    @Override
    public void onGuiInventory(InventoryClickEvent event) {
        OnGuiInventoryEvent p = getPage();
        if (p != null) {
            event.setCancelled(true);
            p.onGuiInventory(event);
        }
    }

    @Override
    public void onPlayerInventory(InventoryClickEvent event) {
        OnGuiInventoryEvent p = getPage();
        if (p != null) {
            event.setCancelled(true);
            p.onPlayerInventory(event);
        }
        event.setCancelled(true);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void initialize2() {
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return getPage().getInventory();
    }
}
