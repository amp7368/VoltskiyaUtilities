package voltskiya.apple.utilities.util.minecraft;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InventoryUtils {
    public static ItemStack makeItem(Material material) {
        return makeItem(material, 1, (String) null, null);
    }

    public static ItemStack makeItem(Material material, String name) {
        return makeItem(material, 1, name, null);
    }

    public static ItemStack makeItem(Material material, int amount, @Nullable String name, @Nullable List<String> lore) {
        final ItemStack item = new ItemStack(material, amount);

        ItemMeta itemMeta = item.getItemMeta();
        if (lore != null)
            itemMeta.setLore(lore);
        if (name != null)
            itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack makeItem(Material material, int amount, @Nullable BaseComponent[] name, @Nullable List<String> lore) {
        final ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        if (name != null)
            itemMeta.setDisplayNameComponent(name);
        item.setItemMeta(itemMeta);
        return item;
    }

    @NotNull
    public static ItemStack addDataString(@NotNull ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull String value) {
        return addData(itemStack, namespacedKey, PersistentDataType.STRING, value);
    }

    @NotNull
    public static <T, Z> ItemStack addData(@NotNull ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull PersistentDataType<T, Z> type, @NotNull Z value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(namespacedKey, type, value);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Nullable
    public static String getString(@Nullable ItemStack itemStack, @NotNull NamespacedKey namespacedKey) {
        return getData(itemStack, namespacedKey, PersistentDataType.STRING);
    }

    @Nullable
    public static <T, Z> Z getData(@Nullable ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull PersistentDataType<T, Z> type) {
        if (itemStack == null) return null;
        else return itemStack.getItemMeta().getPersistentDataContainer().get(namespacedKey, type);
    }
}
