package voltskiya.apple.utilities.action;

import java.util.function.Supplier;

public class ScheduledAction implements RepeatableAction {
    private final String name;
    private final Supplier<Runnable> actionCreator;
    private final int delay;
    private final String runAfter;
    private Runnable action;
    private int currentTick = 0;
    private boolean shouldStart;
    private boolean shouldRun;

    public ScheduledAction(String name, Runnable action, int delay, String runAfter) {
        this.name = name;
        this.action = action;
        this.runAfter = runAfter;
        this.delay = delay;
        this.actionCreator = null;
    }

    public ScheduledAction(String name, Supplier<Runnable> actionCreator, int delay, String runAfter) {
        this.name = name;
        this.actionCreator = actionCreator;
        this.runAfter = runAfter;
        this.delay = delay;
        this.action = null;
    }

    public ScheduledAction(String name, Runnable action, int delay) {
        this.name = name;
        this.action = action;
        this.runAfter = null;
        this.delay = delay;
        this.actionCreator = null;
    }

    public ScheduledAction(String name, Supplier<Runnable> actionCreator, int delay) {
        this.name = name;
        this.actionCreator = actionCreator;
        this.runAfter = null;
        this.delay = delay;
        this.action = null;
    }

    @Override
    public void tick(RepeatingActionManager manager) {
        this.shouldStart = false;
        if (++this.currentTick % delay == 0) {
            getAction().run();
            if (this.runAfter != null) {
                manager.startAction(runAfter);
            }
            this.shouldRun = false;
        }
    }

    private Runnable getAction() {
        if (action == null && actionCreator != null) {
            action = actionCreator.get();
        }
        return action;
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
        this.shouldStart = true;
        this.shouldRun = true;
    }

    @Override
    public void stop() {
        this.shouldRun = false;
    }

    @Override
    public boolean shouldStart() {
        return shouldStart;
    }
}
