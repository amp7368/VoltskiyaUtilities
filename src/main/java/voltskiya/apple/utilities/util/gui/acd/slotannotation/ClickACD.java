package voltskiya.apple.utilities.util.gui.acd.slotannotation;

import org.bukkit.event.inventory.ClickType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickACD {
    ClickType[] action() default {};

    boolean isLeft() default false;

    boolean isRight() default false;

    boolean isShift() default false;

    boolean isKeyboard() default false;

    boolean isCreative() default false;

    String description() default "";

    String descriptionSupplier() default "";

    int valIfTrue() default 0;
}
