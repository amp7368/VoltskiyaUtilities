package voltskiya.apple.utilities.trash;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public interface InventoryUtils {
    PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();

    default ItemStack makeItemI(Material material) {
        return makeItem(material);
    }

    static ItemStack makeItem(Material material) {
        return makeItem(material, 1, (String) null, null);
    }

    default ItemStack makeItemI(Material material, String name) {
        return makeItem(material, name);
    }

    static ItemStack makeItem(Material material, String name) {
        return makeItem(material, 1, name, null);
    }

    default ItemStack makeItemI(Material material, int amount, @Nullable String name, @Nullable List<String> lore) {
        return makeItem(material, amount, name, lore);
    }

    static ItemStack makeItem(Material material, int amount, @Nullable String name, @Nullable List<String> lore) {
        final ItemStack item = new ItemStack(material, amount);

        ItemMeta itemMeta = item.getItemMeta();
        if (lore != null) {
            List<Component> deserialized = lore
                    .stream()
                    .map(l -> serializer.deserializeOr(l, Component.text(l)))
                    .collect(Collectors.toList());
            itemMeta.lore(deserialized);
        }
        if (name != null) itemMeta.displayName(serializer.deserializeOr(name, Component.text(name)));
        item.setItemMeta(itemMeta);
        return item;
    }

    @NotNull
    default ItemStack addDataStringI(@NotNull ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull String value) {
        return addDataString(itemStack, namespacedKey, value);
    }

    static ItemStack addDataString(@NotNull ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull String value) {
        return addData(itemStack, namespacedKey, PersistentDataType.STRING, value);
    }

    @NotNull
    default <T, Z> ItemStack addDataI(@NotNull ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull PersistentDataType<T, Z> type, @NotNull Z value) {
        return addData(itemStack, namespacedKey, type, value);
    }

    static <T, Z> ItemStack addData(@NotNull ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull PersistentDataType<T, Z> type, @NotNull Z value) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(namespacedKey, type, value);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Nullable
    default String getStringI(@Nullable ItemStack itemStack, @NotNull NamespacedKey namespacedKey) {
        return getString(itemStack, namespacedKey);
    }

    static String getString(@Nullable ItemStack itemStack, @NotNull NamespacedKey namespacedKey) {
        return getData(itemStack, namespacedKey, PersistentDataType.STRING);
    }

    @Nullable
    default <T, Z> Z getDataI(@Nullable ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull PersistentDataType<T, Z> type) {
        return getData(itemStack, namespacedKey, type);
    }

    static <T, Z> Z getData(@Nullable ItemStack itemStack, @NotNull NamespacedKey namespacedKey, @NotNull PersistentDataType<T, Z> type) {
        if (itemStack == null) return null;
        else return itemStack.getItemMeta().getPersistentDataContainer().get(namespacedKey, type);
    }

    @NotNull
    default String[] getItemFlagsI(ItemStack item) {
        return getItemFlags(item);
    }

    static String[] getItemFlags(ItemStack item) {
        return InventoryUtils.getItemFlags(item);
    }

    default void addItemFlagsI(ItemStack item, String... flags) {
        addItemFlags(item, flags);
    }

    static void addItemFlags(ItemStack item, String... flags) {
        TagConstants.addItemFlags(item, flags);
    }

    default void setItemFlagsI(ItemStack item, String... flags) {
        setItemFlags(item, flags);
    }

    static void setItemFlags(ItemStack item, String... flags) {
        TagConstants.setItemFlags(item, flags);
    }

    default ItemStack displayNameI(ItemStack item, String name) {
        return displayName(item, name);
    }

    static ItemStack displayName(ItemStack item, String name) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Component.text(name));
        item.setItemMeta(itemMeta);
        return item;
    }
}
