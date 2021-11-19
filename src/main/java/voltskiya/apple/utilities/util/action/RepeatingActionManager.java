package voltskiya.apple.utilities.util.action;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RepeatingActionManager implements Runnable {
    private final Plugin plugin;
    private final List<RepeatableAction> actions = new ArrayList<>();
    private final List<RepeatableAction> actionsToAdd = new ArrayList<>();
    private final Map<String, RepeatableAction> actionsAvailable = new HashMap<>();
    private boolean isRunning;
    private boolean shouldRun = true;
    private Runnable doFinally = null;
    private Runnable init = null;

    public RepeatingActionManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public RepeatingActionManager registerAction(RepeatableAction action) {
        synchronized (actionsAvailable) {
            this.actionsAvailable.put(action.getName(), action);
        }
        return this;
    }

    public RepeatingActionManager registerAndStartAction(RepeatableAction action) {
        synchronized (actionsToAdd) {
            this.actionsToAdd.add(action);
        }
        return registerAction(action);
    }

    public RepeatingActionManager registerFinally(Runnable doFinally) {
        this.doFinally = doFinally;
        return this;
    }

    public RepeatingActionManager registerInit(Runnable init) {
        this.init = init;
        return this;
    }

    public RepeatingActionManager startAction(String actionToStart) {
        @Nullable RepeatableAction action = getAction(actionToStart);
        if (action != null) action.start();
        return this;
    }

    public RepeatingActionManager startActionAndStart(String actionToStart) {
        @Nullable RepeatableAction action = getAction(actionToStart);
        if (action != null) action.start();
        start();
        return this;
    }

    public RepeatingActionManager scheduleAction(String actionToStart, long delay) {
        @Nullable RepeatableAction action = getAction(actionToStart);
        if (action != null) Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, action::start, delay);
        return this;
    }

    public RepeatingActionManager scheduleActionAndStart(String actionToStart, long delay) {
        @Nullable RepeatableAction action = getAction(actionToStart);
        if (action != null) Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            action.start();
            start();
        }, delay);
        return this;
    }

    public RepeatingActionManager stopAction(String actionToStart) {
        @Nullable RepeatableAction action = getAction(actionToStart);
        if (action != null) action.stop();
        return this;
    }

    @Nullable
    public RepeatableAction getAction(String actionToStart) {
        return actionsAvailable.get(actionToStart);
    }

    public RepeatingActionManager start() {
        if (isRunning) return this;
        this.isRunning = true;
        if (init != null) init.run();
        scheduleSelf(0);
        return this;
    }

    public RepeatingActionManager stop() {
        this.shouldRun = false;
        this.actions.clear();
        return this;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void scheduleSelf(int tickingInterval) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this, tickingInterval);
    }

    @Override
    final public void run() {
        synchronized (actionsToAdd) {
            actions.addAll(actionsToAdd);
            actionsToAdd.clear();
        }
        synchronized (actionsAvailable) {
            for (RepeatableAction action : actionsAvailable.values()) {
                if (action.shouldStart()) {
                    actions.add(action);
                }
            }
        }
        if (shouldRun && !actions.isEmpty()) {
            doAction();
            scheduleSelf(1);
        } else {
            this.isRunning = false;
            if (this.doFinally != null)
                this.doFinally.run();
        }
    }

    protected void doAction() {
        synchronized (actionsToAdd) {
            for (Iterator<RepeatableAction> iterator = actions.iterator(); iterator.hasNext(); ) {
                RepeatableAction action = iterator.next();
                if (action.shouldRun() || action.shouldStart())
                    action.tick(this);
                else
                    iterator.remove();
            }
        }
    }

    public void setShouldRun(boolean shouldRun) {
        this.shouldRun = shouldRun;
    }
}
