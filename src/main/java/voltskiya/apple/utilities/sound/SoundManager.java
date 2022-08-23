package voltskiya.apple.utilities.sound;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private final Map<String, SoundAction> sounds = new HashMap<>();

    public SoundManager registerSound(SoundAction sound) {
        this.sounds.put(sound.getName(), sound);
        return this;
    }

    public SoundManager playSound(String sound, Location location) {
        @Nullable SoundAction soundAction = getSound(sound);
        if (soundAction != null) soundAction.play(location);
        return this;
    }

    @Nullable
    public SoundAction getSound(String sound) {
        return sounds.get(sound);
    }
}
