package voltskiya.apple.utilities.action;

public interface RepeatableAction {
    void tick(RepeatingActionManager repeatingActionManager);

    boolean shouldRun();

    String getName();

    void start();

    void stop();

    boolean shouldStart();
}
