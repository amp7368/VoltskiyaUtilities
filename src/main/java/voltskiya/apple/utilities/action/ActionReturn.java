package voltskiya.apple.utilities.action;

public record ActionReturn(boolean shouldRun) {

    public static ActionReturn stop() {
        return new ActionReturn(false);
    }

    public static ActionReturn go() {
        return new ActionReturn(true);
    }
}
