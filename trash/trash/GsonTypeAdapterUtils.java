package voltskiya.apple.utilities.trash;

import apple.nms.decoding.entity.DecodeEntity;
import apple.nms.decoding.iregistry.DecodeEntityTypes;
import apple.nms.decoding.iregistry.DecodeIRegistry;
import apple.nms.decoding.nbt.DecodeNBT;
import apple.nms.decoding.world.DecodeMinecraftKey;
import apple.utilities.json.gson.GsonBuilderDynamic;
import apple.utilities.json.gson.serialize.JsonSerializing;
import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.core.IRegistry;
import net.minecraft.nbt.MojangsonParser;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;
import java.util.UUID;

public class GsonTypeAdapterUtils {
    public static GsonBuilder registerLocationTypeAdapter(GsonBuilder gson, LocationTypeAdapterOptions options) {
        return gson.registerTypeHierarchyAdapter(Location.class, getLocationTypeAdapter(options));
    }

    public static GsonBuilderDynamic registerLocationTypeAdapter(GsonBuilderDynamic gson, LocationTypeAdapterOptions options) {
        return gson.registerTypeHierarchyAdapter(Location.class, getLocationTypeAdapter(options));
    }

    public static LocationJsonTypeAdapter getLocationTypeAdapter(LocationTypeAdapterOptions options) {
        return new LocationJsonTypeAdapter(options);
    }

    public static GsonBuilder registerNBTTagTypeAdapter(GsonBuilder gson) {
        return gson.registerTypeHierarchyAdapter(NBTTagCompound.class, getNBTTagTypeAdapter());
    }

    public static GsonBuilderDynamic registerNBTTagTypeAdapter(GsonBuilderDynamic gson) {
        return gson.registerTypeHierarchyAdapter(NBTTagCompound.class, getNBTTagTypeAdapter());
    }

    public static NBTTagJsonTypeAdapter getNBTTagTypeAdapter() {
        return NBTTagJsonTypeAdapter.get();
    }

    public static GsonBuilder registerMinecraftKeyTypeAdapter(GsonBuilder gson) {
        return gson.registerTypeHierarchyAdapter(MinecraftKey.class, getMinecraftKeyTypeAdapter());
    }

    public static GsonBuilderDynamic registerMinecraftKeyTypeAdapter(GsonBuilderDynamic gson) {
        return gson.registerTypeHierarchyAdapter(MinecraftKey.class, getMinecraftKeyTypeAdapter());
    }

    public static MinecraftKeyJsonTypeAdapter getMinecraftKeyTypeAdapter() {
        return MinecraftKeyJsonTypeAdapter.get();
    }

    public static GsonBuilder registerEntityTypesTypeAdapter(GsonBuilder gson) {
        return gson.registerTypeHierarchyAdapter(EntityTypes.class, getEntityTypesAdapter());
    }

    public static GsonBuilderDynamic registerEntityTypesTypeAdapter(GsonBuilderDynamic gson) {
        return gson.registerTypeHierarchyAdapter(EntityTypes.class, getEntityTypesAdapter());
    }

    public static EntityTypesJsonTypeAdapter getEntityTypesAdapter() {
        return EntityTypesJsonTypeAdapter.get();
    }

    public record LocationJsonTypeAdapter(
            LocationTypeAdapterOptions options)
            implements JsonSerializer<Location>, JsonDeserializer<Location> {

        @Override
        public JsonElement serialize(Location location, Type type, JsonSerializationContext context) {
            JsonObject json = new JsonObject();
            if (options().isWorldIncluded()) {
                json.add("world", context.serialize(location.getWorld().getUID()));
            }
            if (options().isPreciseDoubleLocation()) {
                json.add("x", new JsonPrimitive(location.getX()));
                json.add("y", new JsonPrimitive(location.getY()));
                json.add("z", new JsonPrimitive(location.getZ()));
            } else {
                json.add("x", new JsonPrimitive(location.getBlockX()));
                json.add("y", new JsonPrimitive(location.getBlockY()));
                json.add("z", new JsonPrimitive(location.getBlockZ()));
            }
            if (options().isDirectionIncluded()) {
                json.add("yaw", new JsonPrimitive(location.getYaw()));
                json.add("pitch", new JsonPrimitive(location.getPitch()));
            }
            return json;
        }

        @Override
        public Location deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject json = jsonElement.getAsJsonObject();

            World world;
            if (options().isWorldIncluded()) {
                UUID uuid = context.deserialize(json.get("world"), UUID.class);
                world = Bukkit.getWorld(uuid);
            } else {
                world = Bukkit.getWorlds().get(0);
            }
            float yaw = 0, pitch = 0;
            if (options().isDirectionIncluded()) {
                yaw = json.get("yaw").getAsFloat();
                pitch = json.get("pitch").getAsFloat();
            }
            double x, y, z;
            if (options().isPreciseDoubleLocation()) {
                x = json.get("x").getAsDouble();
                y = json.get("y").getAsDouble();
                z = json.get("z").getAsDouble();
            } else {
                x = json.get("x").getAsInt();
                y = json.get("y").getAsInt();
                z = json.get("z").getAsInt();
            }
            return new Location(world, x, y, z, yaw, pitch);
        }
    }

    public static record LocationTypeAdapterOptions(boolean isPreciseDoubleLocation,
                                                    boolean isDirectionIncluded,
                                                    boolean isWorldIncluded) {
    }

    private static class NBTTagJsonTypeAdapter implements JsonSerializing<NBTTagCompound> {
        private static final NBTTagJsonTypeAdapter instance = new NBTTagJsonTypeAdapter();

        public static NBTTagJsonTypeAdapter get() {
            return instance;
        }

        @Override
        public NBTTagCompound deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                return DecodeNBT.parse(jsonElement.getAsString());
            } catch (CommandSyntaxException e) {
                throw new JsonParseException(e);
            }
        }

        @Override
        public JsonElement serialize(NBTTagCompound nbtTagCompound, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(nbtTagCompound.toString());
        }
    }

    private static class EntityTypesJsonTypeAdapter implements JsonSerializing<EntityTypes<?>> {
        private static final EntityTypesJsonTypeAdapter instance = new EntityTypesJsonTypeAdapter();

        public static EntityTypesJsonTypeAdapter get() {
            return instance;
        }

        @Override
        public EntityTypes<?> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return DecodeIRegistry.get(DecodeIRegistry.getEntityType(), DecodeMinecraftKey.makeKey(jsonElement.getAsString()));
        }

        @Override
        public JsonElement serialize(EntityTypes<?> entityTypes, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(entityTypes.j().toString());
        }
    }

    private static class MinecraftKeyJsonTypeAdapter implements JsonSerializing<MinecraftKey> {
        private static final MinecraftKeyJsonTypeAdapter instance = new MinecraftKeyJsonTypeAdapter();

        public static MinecraftKeyJsonTypeAdapter get() {
            return instance;
        }

        @Override
        public MinecraftKey deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return MinecraftKey.a(jsonElement.getAsString());
        }

        @Override
        public JsonElement serialize(MinecraftKey minecraftKey, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(minecraftKey.toString());
        }
    }
}
