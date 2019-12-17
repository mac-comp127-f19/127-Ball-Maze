package ballMaze;

import comp127graphics.CanvasWindow;
import comp127graphics.Rectangle;

import java.awt.*;

public class Wall extends Rectangle {
    public static final Color color = new Color(12, 44, 24, 162);
    private double width;
    private double height;

    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
        this.setStrokeColor(color);
        this.setFillColor(color);
    }
}
