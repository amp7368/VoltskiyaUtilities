package voltskiya.apple.utilities.util.action;

@FunctionalInterface
public interface ActionToRun {
    boolean run(int currentTick, boolean isLastRun);
}
