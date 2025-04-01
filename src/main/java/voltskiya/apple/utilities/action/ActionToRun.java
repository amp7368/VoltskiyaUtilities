package voltskiya.apple.utilities.action;

@FunctionalInterface
public interface ActionToRun {

    ActionReturn run(ActionMeta info);
}
