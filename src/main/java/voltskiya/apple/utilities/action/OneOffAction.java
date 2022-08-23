package voltskiya.apple.utilities.action;

public class OneOffAction implements RepeatableAction {
    private final Runnable runnable;
    private final String name;
    private boolean shouldRun = false;

    public OneOffAction(String name, Runnable runnable) {
        this.runnable = runnable;
        this.name = name;
    }

    @Override
    public void tick(RepeatingActionManager repeatingActionManager) {
        this.runnable.run();
        this.shouldRun = false;
    }

    @Override
    public boolean shouldRun() {
        return shouldRun;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void start() {
        this.shouldRun = true;
    }

    @Override
    public void stop() {
        this.shouldRun = false;
    }

    @Override
    public boolean shouldStart() {
        return this.shouldRun;
    }
}
