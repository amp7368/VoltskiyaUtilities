package voltskiya.apple.utilities.util.action;

import java.util.function.Supplier;

public class RepeatableActionImpl implements RepeatableAction {
    private final String name;
    private final Supplier<ActionToRun> actionCreator;
    private final int tickingInterval;
    private final int ticksToRunForOriginal;
    private int currentTick;
    private ActionToRun action;
    private int ticksToRunFor;
    private boolean shouldStart;
    private boolean shouldRun;

    public RepeatableActionImpl(String name, ActionToRun action, int ticksToRunFor, int tickingInterval) {
        this.name = name;
        this.action = action;
        this.ticksToRunForOriginal = this.ticksToRunFor = ticksToRunFor;
        this.currentTick = 0;
        this.tickingInterval = tickingInterval;
        actionCreator = null;
    }

    public RepeatableActionImpl(String name, Supplier<ActionToRun> actionCreator, int ticksToRunFor, int tickingInterval) {
        this.name = name;
        this.actionCreator = actionCreator;
        this.ticksToRunForOriginal = this.ticksToRunFor = ticksToRunFor;
        this.currentTick = 0;
        this.tickingInterval = tickingInterval;
        action = null;
    }

    @Override
    public void tick() {
        this.shouldStart = false;
        if (++this.currentTick % tickingInterval == 0) {
            this.shouldRun = getAction().run(this.currentTick, this.ticksToRunFor <= 1);
        }
        this.ticksToRunFor--;
    }

    private ActionToRun getAction() {
        if (action == null && actionCreator != null) {
            action = actionCreator.get();
        }
        return action;
    }

    @Override
    public boolean shouldRun() {
        return this.ticksToRunFor > 0 && shouldRun;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void start() {
        this.ticksToRunFor = ticksToRunForOriginal;
        this.shouldStart = true;
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
