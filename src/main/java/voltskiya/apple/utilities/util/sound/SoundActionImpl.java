package voltskiya.apple.utilities.util.sound;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;

public record SoundActionImpl(String name, Sound sound,
                              SoundCategory category,
                              float pitch, float volume) implements SoundAction {
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getPitch() {
        return this.pitch;
    }

    @Override
    public float getVolume() {
        return this.volume;
    }

    @Override
    public SoundCategory getCategory() {
        return this.category;
    }

    @Override
    public Sound getSound() {
        return this.sound;
    }
}
