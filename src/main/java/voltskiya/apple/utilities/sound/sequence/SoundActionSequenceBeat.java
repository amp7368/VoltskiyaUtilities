package voltskiya.apple.utilities.sound.sequence;

import java.util.function.Supplier;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import voltskiya.apple.utilities.sound.SoundAction;

public class SoundActionSequenceBeat {

    private final int delay;
    private final Supplier<SoundAction> soundSupplier;

    public SoundActionSequenceBeat(int delay, Supplier<SoundAction> soundSupplier) {
        this.delay = delay;
        this.soundSupplier = soundSupplier;
    }

    public int getDelay() {
        return this.delay;
    }

    private SoundAction sound() {
        return soundSupplier.get();
    }

    public void play(Location location) {
        sound().play(location);
    }

    public void play(Player player) {
        sound().play(player);
    }

    public void play(Supplier<Location> location) {
        sound().play(location);
    }
}
