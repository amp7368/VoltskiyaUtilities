package voltskiya.apple.utilities.chance;

import java.awt.geom.Point2D;

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
}
