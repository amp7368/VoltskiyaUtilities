package voltskiya.apple.utilities.sound;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;

public class SoundActionImpl extends PlaySound {

    public SoundActionImpl(String name, Sound sound, SoundCategory category, float pitch, float volume) {
        super(name, sound, category, pitch, volume);
    }
}
