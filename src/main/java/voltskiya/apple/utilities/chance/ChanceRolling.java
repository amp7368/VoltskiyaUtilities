package voltskiya.apple.utilities.chance;

public class ChanceRolling extends Chance {


    private double multiplier;
    private double minRoll;
    private double successRate;

    public ChanceRolling(double minRoll, double multiplier) {
        this.minRoll = minRoll;
        this.multiplier = multiplier;
        this.resetMinChance();
    }

    private void resetMinChance() {
        this.successRate = 1 - Math.pow(1 - this.minRoll, this.multiplier);
    }

    public double getMinRoll() {
        return this.minRoll;
    }

    public void setMinRoll(double minRoll) {
        this.minRoll = minRoll;
        this.resetMinChance();
    }

    public double getMultiplier() {
        return this.multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
        this.resetMinChance();
    }

    public double getSuccessRate() {
        return this.successRate;
    }

    public boolean rollToActivate(double roll) {
        return roll <= this.successRate;
    }

    public boolean roll() {
        synchronized (random) {
            return random.nextDouble() <= successRate;
        }
    }
}
