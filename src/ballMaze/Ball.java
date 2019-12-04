package ballMaze;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

/**
 * Creates a ball object. The ball will update its position every second as time goes.
 */
public class Ball extends Ellipse {
    public static final double GRAVITY = -0.01;

    private double x;
    private double y;
    private double r;
    private double width;
    private double height;
    private double dx;
    private double sy;
    private double dy;
    private CanvasWindow canvas;
    private Maze maze;
    private double boundWidth;
    private double boundHeight;

    public Ball(double x, double y, double r, CanvasWindow canvas) {
        super(x, y, 2 * r, 2 * r);
        this.x = x;
        this.y = y;
        this.r = r;
        width = 2 * r;
        height = 2 * r;
        this.canvas = canvas;
        dx = 0.5;
        dy = -0.5;
//        boundHeight = canvas.getHeight() + maze.getHeight();
//        boundWidth = canvas.getWidth() + maze.getWidth();
        this.maze = maze;
    }

    /**
     * Updates the position of the ball if it's inside the box.
     */
    public void move() {
        if (x > 0 && x < canvas.getWidth() && y > 0 && y < canvas.getHeight()){
            x += dx;
            y += dy;
            this.setCenter(x, y);
            dy -= GRAVITY;
        }
        else{
            stop();
        }
    }

    /**
     * Stops the ball by setting dx and dy to be 0.
     */
    public void stop(){
        dx = 0;
        dy = 0;
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
