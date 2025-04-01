package voltskiya.apple.utilities.action;

import java.util.function.Supplier;

public class RepeatableActionImpl implements RepeatableAction {

    private final String name;
    private final int tickingInterval;
    private final int ticksToRunForOriginal;
    private Supplier<ActionToRun> actionCreator;
    private int currentTick;
    private ActionToRun action;
    private int ticksToRunFor;
    private boolean shouldStart;
    private boolean shouldRun;
    private int currentRepeat = 0;

    private RepeatableActionImpl(String name, int ticksToRunFor, int tickingInterval) {
        this.name = name;
        this.ticksToRunForOriginal = this.ticksToRunFor = ticksToRunFor;
        this.currentTick = 0;
        this.tickingInterval = tickingInterval;
        actionCreator = null;
    }

    public RepeatableActionImpl(String name, ActionToRun action, int ticksToRunFor, int tickingInterval) {
        this(name, ticksToRunFor, tickingInterval);
        this.action = action;
        actionCreator = null;
    }

    public RepeatableActionImpl(String name, Supplier<ActionToRun> actionCreator, int ticksToRunFor, int tickingInterval) {
        this(name, ticksToRunFor, tickingInterval);
        action = null;
        this.actionCreator = actionCreator;
    }

    @Override
    public void tick(RepeatingActionManager repeatingActionManager) {
        this.shouldStart = false;
        if (this.currentTick++ % tickingInterval == 0) {
            ActionMeta info = new ActionMeta(this.currentTick, this.currentRepeat++, this.ticksToRunFor <= tickingInterval);
            ActionReturn run = getAction().run(info);
            this.shouldRun = run.shouldRun();
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
        boolean should = this.ticksToRunFor > 0 && shouldRun;
        if (!should) {
            if (this.actionCreator != null)
                this.action = null;
        }
        return should;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void start() {
        this.ticksToRunFor = ticksToRunForOriginal;
        this.shouldStart = true;
        this.shouldRun = true;
    }

    @Override
    public void stop() {
        this.shouldRun = false;
        this.currentTick = 0;
        if (this.actionCreator != null)
            this.action = null;
    }

    @Override
    public boolean shouldStart() {
        return shouldStart;
    }
}
