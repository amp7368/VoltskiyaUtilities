package voltskiya.apple.utilities.chance;

public class ChanceRolling extends Chance {


    private double multiplier;
    private double minRoll;
    private double successRate;

    public ChanceRolling(double minRoll, double multiplier) {
        this.minRoll = minRoll;
        this.multiplier = multiplier;
    }

    private void resetMinChance() {
        this.successRate = 1 - Math.pow(1 - this.minRoll, this.multiplier);
    }

    public void setMinRoll(double minRoll) {
        this.minRoll = minRoll;
        this.resetMinChance();
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
        this.resetMinChance();
    }

    public double getMinRoll() {
        return this.minRoll;
    }

    public double getMultiplier() {
        return this.multiplier;
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
