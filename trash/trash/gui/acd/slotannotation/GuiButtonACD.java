package voltskiya.apple.utilities.trash.gui.acd.slotannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GuiButtonACD {
    ItemACD item();

    int[] slots();

    ClickACD[] onClick() default {};
}
