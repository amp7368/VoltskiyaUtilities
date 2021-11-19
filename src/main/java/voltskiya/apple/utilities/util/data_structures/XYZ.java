package voltskiya.apple.utilities.util.data_structures;

import org.bukkit.util.Vector;

public class XYZ<T> extends Triple<T, T, T> {
    public XYZ(T x, T y, T z) {
        super(x, y, z);
    }

    public static XYZ<Double> from(Vector vector) {
        return new XYZ<>(vector.getX(), vector.getY(), vector.getZ());
    }
}
