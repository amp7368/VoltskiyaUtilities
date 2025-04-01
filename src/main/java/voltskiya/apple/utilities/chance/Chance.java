package voltskiya.apple.utilities.chance;

import java.util.Random;

public class Chance {

    protected final Random random = new Random();

    public static double complimentChance(double chance) {
        return 1 - chance;
    }

    public Random random() {
        return random;
    }
}
