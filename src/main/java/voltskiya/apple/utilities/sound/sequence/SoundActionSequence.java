package voltskiya.apple.utilities.sound.sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import voltskiya.apple.utilities.sound.SoundAction;

public class SoundActionSequence extends SoundAction {

    private final List<SoundActionSequenceBeat> beats = new ArrayList<>();

    public SoundActionSequence(String name) {
        super(name);
    }

    public SoundActionSequence addBeat(int tick, Supplier<SoundAction> sound) {
        this.beats.add(new SoundActionSequenceBeat(tick, sound));
        return this;
    }

    private void schedule(Runnable runnable, int delay) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin(), runnable, delay);
    }

    @Override
    public void play(Supplier<Location> location) {
        for (SoundActionSequenceBeat beat : beats)
            schedule(() -> beat.play(location), beat.getDelay());
    }

    @Override
    public void play(Location location) {
        for (SoundActionSequenceBeat beat : beats)
            schedule(() -> beat.play(location), beat.getDelay());
    }

    @Override
    public void play(Player player) {
        for (SoundActionSequenceBeat beat : beats)
            schedule(() -> beat.play(player), beat.getDelay());
    }
}
