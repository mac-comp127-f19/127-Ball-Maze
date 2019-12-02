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
    private double dy;
    private CanvasWindow canvasWindow;
    private Maze maze;
    private double boundWidth;
    private double boundHeight;

    public Ball(double x, double y, double r, CanvasWindow canvasWindow, Maze maze) {
        super(x, y, 2 * r, 2 * r);
        this.x = x;
        this.y = y;
        this.r = r;
        width = 2 * r;
        height = 2 * r;
        this.canvasWindow = canvasWindow;
        dx = 0.05;
        dy = 0.05;
        boundHeight = canvasWindow.getHeight() + maze.getHeight();
        boundWidth = canvasWindow.getWidth() + maze.getWidth();
        this.maze = maze;
    }


    /**
     * Updates the position of the ball if it's inside the box.
     */
    public void move(double dt) {
        if (x > maze.getX() && x < maze.getX() + maze.getWidth() && y > maze.getY() && y < maze.getY() + maze.getHeight()) {
            x = x + dx * dt;
            y = y + dy * dt;
            this.setCenter(x, y);
            dy -= GRAVITY * dt;
        }
        System.out.println("--------ERROR: OUT OF BOUND---------");
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
