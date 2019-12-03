package ballMaze;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

public class Ball extends Ellipse {
    public static final double GRAVITY = -9.8;

    private double x;
    private double y;
    private double r;
    private double width;
    private double height;
    private double dx;
    private double sy;
    private double dy;
    private CanvasWindow canvasWindow;
    private Maze maze;
    private double boundWidth;
    private double boundHeight;

    public Ball(double x, double y, double r, CanvasWindow canvasWindow) {
        super(x, y, 2 * r, 2 * r);
        this.x = x;
        this.y = y;
        this.r = r;
        width = 2 * r;
        height = 2 * r;
        this.canvasWindow = canvasWindow;
        dx = 5;
        dy = 5;
//        boundHeight = canvasWindow.getHeight() + maze.getHeight();
//        boundWidth = canvasWindow.getWidth() + maze.getWidth();
        this.maze = maze;
    }


    /**
     * Updates the position of the ball if it's inside the box.
     */
    public void move() {
        x += dx;
        y += dy;
        System.out.println(x + "   " + y);
        this.setCenter(x, y);
    }

    public double getR() {
        return r;
    }

    public double getX() {
        return x + r;
    }

    public double getY() {
        return y + r;
    }

}
