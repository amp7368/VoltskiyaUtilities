package voltskiya.apple.utilities.util.wand;

import org.bukkit.event.block.Action;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WandUse {
    Action[] action() default {Action.LEFT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.PHYSICAL, Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK};
}
