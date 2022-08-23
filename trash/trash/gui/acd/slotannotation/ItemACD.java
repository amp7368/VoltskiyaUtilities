package voltskiya.apple.utilities.trash.gui.acd.slotannotation;

import org.bukkit.Material;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemACD {
    Material material();

    int count() default 1;

    String name() default "";

    String[] lore() default {};

    String[] nameSupplier() default "";
}
