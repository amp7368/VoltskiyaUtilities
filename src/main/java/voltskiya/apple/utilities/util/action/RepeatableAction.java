package voltskiya.apple.utilities.util.action;

public interface RepeatableAction {
    void tick(RepeatingActionManager repeatingActionManager);

    boolean shouldRun();

    String getName();

    void start();

    void stop();

    boolean shouldStart();
}
