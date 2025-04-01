package voltskiya.apple.utilities.sound.proxy;

import java.util.function.Supplier;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import voltskiya.apple.utilities.sound.SoundAction;

public class SoundActionDynamic extends SoundAction {

    private final Supplier<SoundAction> supplier;

    public SoundActionDynamic(String name, Supplier<SoundAction> supplier) {
        super(name);
        this.supplier = supplier;
    }

    @Override
    public void play(Supplier<Location> location) {
        supplier.get().play(location);
    }

    @Override
    public void play(Location location) {
        supplier.get().play(location);
    }

    @Override
    public void play(Player player) {
        supplier.get().play(player);
    }
}
