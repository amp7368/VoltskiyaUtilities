package voltskiya.apple.utilities.sound;

import java.util.function.Supplier;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.UtilitiesPlugin;
import voltskiya.apple.utilities.sound.proxy.SoundActionDynamic;
import voltskiya.apple.utilities.sound.sequence.SoundActionSequence;

public abstract class SoundAction {

    private final String name;
    private JavaPlugin plugin;

    protected SoundAction(String name) {
        this.name = name;
    }

    public static PlaySound create(
        String name,
        Sound sound,
        SoundCategory category,
        float pitch,
        float volume
    ) {
        return new PlaySound(name, sound, category, pitch, volume);
    }

    public static SoundActionDynamic createDynamic(String name, Supplier<SoundAction> supplier) {
        return new SoundActionDynamic(name, supplier);
    }

    public static SoundActionSequence createSequence(String name) {
        return new SoundActionSequence(name);
    }

    public abstract void play(Supplier<Location> location);

    public abstract void play(Location location);

    public abstract void play(Player player);

    public String getName() {
        return this.name;
    }

    public void register(SoundManager manager) {
        this.plugin = manager.plugin();
    }

    @NotNull
    protected JavaPlugin plugin() {
        if (this.plugin == null) return UtilitiesPlugin.get();
        return plugin;
    }
}
