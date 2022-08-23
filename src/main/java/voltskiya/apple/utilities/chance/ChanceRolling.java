package voltskiya.apple.utilities.chance;

public class ChanceRolling extends Chance {
    private double minRoll;
    private double minRollComp;

    public ChanceRolling(double minRoll) {
        this.minRoll = minRoll;
        this.minRollComp = complimentChance(minRoll);
    }

    public void setMinRoll(double minRoll) {
        this.minRoll = minRoll;
        this.minRollComp = complimentChance(minRoll);
    }

    public boolean roll() {
        return random.nextDouble() <= minRoll;
    }

    public boolean rollXTimes(double multiplier) {
        return random.nextDouble() <= complimentChance(Math.pow(minRollComp, multiplier));
    }
}
