package voltskiya.apple.utilities.util.minecraft;

import net.minecraft.nbt.NBTTagCompound;

public class NbtUtils {
    public static final String ENTITY_TAG_NBT = "EntityTag";
    public static final String ITEM_TAG = "tag";

    public static NBTTagCompound getItemEntity(NBTTagCompound nbt) {
        return getItemTag(getEntityTag(nbt));
    }

    public static NBTTagCompound getItemTag(NBTTagCompound nbt) {
        return nbt.getCompound(ITEM_TAG);
    }

    public static NBTTagCompound getEntityTag(NBTTagCompound nbt) {
        return nbt.getCompound(ENTITY_TAG_NBT);
    }
}
