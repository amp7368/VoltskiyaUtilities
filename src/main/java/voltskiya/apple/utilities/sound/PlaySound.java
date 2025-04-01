package voltskiya.apple.utilities.sound;

import java.util.function.Supplier;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class PlaySound extends SoundAction {

    private final Sound sound;
    private final SoundCategory category;
    private final float pitch;
    private final float volume;

    public PlaySound(
        String name,
        Sound sound,
        SoundCategory category,
        float pitch,
        float volume
    ) {
        super(name);
        this.sound = sound;
        this.category = category;
        this.pitch = pitch;
        this.volume = volume;
    }


    public Sound getSound() {
        return sound;
    }

    public SoundCategory getCategory() {
        return category;
    }

    public float getPitch() {
        return pitch;
    }

    public float getVolume() {
        return volume;
    }

    @Override
    public void play(Supplier<Location> location) {
        this.play(location.get());
    }

    @Override
    public void play(Location location) {
        location.getWorld().playSound(location, this.getSound(), this.getCategory(), this.getVolume(), this.getPitch());
    }

    @Override
    public void play(Player player) {
        player.playSound(player.getLocation(), this.getSound(), this.getCategory(), this.getVolume(), this.getPitch());
    }
}
