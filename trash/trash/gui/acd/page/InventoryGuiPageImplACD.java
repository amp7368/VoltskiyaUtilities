package voltskiya.apple.utilities.trash.gui.acd.page;

import apple.utilities.util.ArrayUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.trash.gui.OnGuiInventoryEvent;
import voltskiya.apple.utilities.trash.gui.acd.slot.InventoryGuiSlotACD;
import voltskiya.apple.utilities.trash.gui.acd.slot.InventoryGuiSlotDoNothingACD;
import voltskiya.apple.utilities.trash.gui.acd.slot.InventoryGuiSlotImplACD;
import voltskiya.apple.utilities.trash.gui.acd.slotannotation.ClickACD;
import voltskiya.apple.utilities.trash.gui.acd.slotannotation.GuiButtonACD;
import voltskiya.apple.utilities.trash.gui.acd.slotannotation.GuiNameSupplier;
import voltskiya.apple.utilities.trash.InventoryUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Supplier;

public abstract class InventoryGuiPageImplACD<Parent extends GuiPageable> implements InventoryGuiPageACD, GuiPageable {
    protected final InventoryGuiSlotACD[] clicking = new InventoryGuiSlotACD[size()];
    private final Map<String, Supplier<Object[]>> nameSuppliers = new HashMap<>();

    private final Inventory inventory;
    protected Parent parent;

    public InventoryGuiPageImplACD(Parent parent) {
        this.inventory = Bukkit.createInventory(parent, size(), Component.text(getName()));
        this.parent = parent;
        Arrays.fill(clicking, InventoryGuiSlotDoNothingACD.EMPTY);
        reflectionButtions();
    }

    private void reflectionButtions() {
        for (Method method : getClass().getDeclaredMethods()) {
            GuiButtonACD guiButton = method.getAnnotation(GuiButtonACD.class);
            if (guiButton != null) {
                ClickACD[] clicks = guiButton.onClick();
                List<String> descriptions = new ArrayList<>();
                for (ClickACD click : clicks) {
                    if (!click.description().isEmpty()) {
                        descriptions.add(click.description());
                    }
                }
                Supplier<ItemStack> itemSupplier = () -> InventoryUtils.makeItem(
                        guiButton.item().material(),
                        guiButton.item().count(),
                        String.format(guiButton.item().name(), getNameSupplier(guiButton.item().nameSupplier())),
                        new ArrayList<>(List.of(guiButton.item().lore())) {{
                            addAll(descriptions);
                        }}
                );

                InventoryGuiSlotACD onClick = new InventoryGuiSlotImplACD((e) -> {
                    boolean shouldRun = false;
                    int valIfTrue = 0;
                    for (ClickACD click : clicks) {
                        for (ClickType clickType : click.action()) {
                            if (clickType == e.getClick()) {
                                shouldRun = true;
                                break;
                            }
                        }
                        if (!shouldRun) {
                            if (click.isCreative() && e.getClick().isCreativeAction()) {
                                shouldRun = true;
                            } else if (click.isKeyboard() && e.getClick().isKeyboardClick()) {
                                shouldRun = true;
                            } else if (click.isLeft() && e.getClick().isLeftClick()) {
                                shouldRun = true;
                            } else if (click.isShift() && e.getClick().isShiftClick()) {
                                shouldRun = true;
                            } else if (click.isRight() && e.getClick().isRightClick()) {
                                shouldRun = true;
                            }
                        }
                        if (shouldRun) {
                            valIfTrue = click.valIfTrue();
                            break;
                        }
                    }

                    if (shouldRun) {
                        try {
                            method.invoke(this, e, valIfTrue);
                            refresh();
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    }
                }, itemSupplier);
                for (int slot : guiButton.slots()) {
                    clicking[slot] = onClick;
                }
            } else {
                GuiNameSupplier guiNameSupplier = method.getAnnotation(GuiNameSupplier.class);
                if (guiNameSupplier != null) {
                    this.nameSuppliers.put(guiNameSupplier.nameSupplier(), () -> {
                        Object returned;
                        try {
                            returned = method.invoke(this);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            return new Object[0];
                        }
                        if (returned instanceof Object[] obj) return obj;
                        else return new Object[]{returned};
                    });
                }
            }
        }
    }

    private Object[] getNameSupplier(String[] nameSupplier) {
        Object[] supplied = new Object[0];
        for (String name : nameSupplier) {
            supplied = ArrayUtils.combineObjectArrays(supplied, getNameSupplier(name), Object[]::new);
        }
        return supplied;
    }

    private Object[] getNameSupplier(String name) {
        Supplier<Object[]> s = this.nameSuppliers.get(name);
        return s == null ? new Object[0] : s.get();
    }

    @Override
    public void setSlot(InventoryGuiSlotACD item, int... slot) {
        for (Integer i : slot) {
            clicking[i] = item;
        }
    }

    @Override
    public void parentAddSubPage(OnGuiInventoryEvent subPage) {
        parent.parentAddSubPage(subPage);
    }

    @Override
    public void parentRemoveSubPage() {
        parent.parentRemoveSubPage();
    }

    @Override
    public void parentNext(int i) {
        parent.parentNext(i);
    }

    @Override
    public void parentRefresh() {
        refresh();
        parent.parentRefresh();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void initialize2() {
        refresh();
    }

    @Override
    public void onGuiInventory(InventoryClickEvent event) {
        int slot = event.getSlot();
        if (slot < 0 || slot >= clicking.length) return;
        clicking[slot].dealWithClick(event);
        parentRefresh();
    }

    @Override
    public void onPlayerInventory(InventoryClickEvent event) {
    }

    public void finalizePageItems() {
        for (int i = 0; i < clicking.length; i++) {
            this.getInventory().setItem(i, clicking[i].getItem());
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @Override
    abstract public int size();
}
