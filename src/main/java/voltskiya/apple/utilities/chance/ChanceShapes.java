package voltskiya.apple.utilities.chance;

import java.awt.geom.Point2D;
import org.bukkit.util.Vector;

public class ChanceShapes extends Chance {

    public Point2D.Double circle(double radius) {
        double theta = random.nextDouble() * 360;
        return new Point2D.Double(Math.cos(theta) * radius, Math.sin(theta) * radius);
    }

    public Point2D.Double circle(double innerRadius, double outerRadius) {
        double theta = random.nextDouble() * 360;
        double radius = (outerRadius - innerRadius) * Math.sqrt(random.nextDouble()) + innerRadius;
        return new Point2D.Double(Math.cos(theta) * radius, Math.sin(theta) * radius);
    }

    public Vector sphere(double radius) {
        double u = Math.pow(random.nextDouble(), 1 / 3d);
        double x = random.nextGaussian() - 0.5;
        double y = random.nextGaussian() - 0.5;
        double z = random.nextGaussian() - 0.5;
        Vector vector = new Vector(x, y, z);
        vector.normalize();
        vector.multiply(u * radius);
        return vector;
    }
}
