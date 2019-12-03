package ballMaze;

import comp127graphics.CanvasWindow;
import comp127graphics.Rectangle;

public class Wall extends Rectangle {
    private double width;
    private double height;

    public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
    }
}
