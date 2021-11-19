package voltskiya.apple.utilities.util.sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;

public interface SoundAction {
    default void play(Location location) {
        location.getWorld().playSound(location, getSound(), getCategory(), getVolume(), getPitch());
    }

    String getName();

    float getPitch();

    float getVolume();

    SoundCategory getCategory();

    Sound getSound();
}
