package voltskiya.apple.utilities.sound;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public class SoundManager {

    private final Map<String, SoundAction> sounds = new HashMap<>();
    @Nullable
    private JavaPlugin plugin;

    public SoundManager() {
    }

    public SoundManager(@Nullable JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public SoundManager registerSound(SoundAction sound) {
        sound.register(this);
        this.sounds.put(sound.getName(), sound);
        return this;
    }

    public SoundManager playSound(String sound, Location location) {
        @Nullable SoundAction soundAction = getSound(sound);
        if (soundAction != null) soundAction.play(location);
        return this;
    }

    public SoundManager playSound(String sound, Player location) {
        @Nullable SoundAction soundAction = getSound(sound);
        if (soundAction != null) soundAction.play(location);
        return this;
    }

    public SoundManager playSound(String sound, Supplier<Location> location) {
        @Nullable SoundAction soundAction = getSound(sound);
        if (soundAction != null) soundAction.play(location);
        return this;
    }

    @Nullable
    public SoundAction getSound(String sound) {
        return sounds.get(sound);
    }

    @Nullable
    public JavaPlugin plugin() {
        return this.plugin;
    }
}
