package voltskiya.apple.utilities.util.data_structures;

import java.util.Locale;

public class StringPretty {
    public static String uppercaseFirst(String s) {
        if (s == null) return null;
        s = s.toLowerCase(Locale.ROOT);
        char[] chars = s.toCharArray();
        if (chars.length > 0) {
            chars[0] = Character.toUpperCase(chars[0]);
        }
        return new String(chars);
    }
}
