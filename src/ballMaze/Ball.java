package ballMaze;

import comp127graphics.Ellipse;

import java.awt.*;

/**
 * Creates a ball object. The ball will update its position every second as time goes.
 */
public class Ball extends Ellipse {
    private static final Color darkRed = new Color(48, 19, 5, 255);
    private static final double GRAVITY = -0.02;

    private double dx;
    private double dy;
    private double x;
    private double y;
    private double r;

    public Ball(double x, double y, double r) {
        super(x, y, 2 * r, 2 * r);
        this.x = x;
        this.y = y;
        this.r = r;
        dx = 1;
        dy = 1;
        this.setFillColor(darkRed);
    }

    public boolean collideWithPaddle(Paddle paddle){
        if (getElementAt(x, y + r) == paddle){
            this.setPosition(x, paddle.getTopY());
            dy = - dy;
            return true;
        }
        return false;
    }

    /**
     * Checks the four midpoints of the rectangle around the ball to see if there is any GraphicsObject there.
     * @param paddle that wants to be check if it interacts with the ball.
     * @return the point where that interaction happens.
     */
    public boolean inspectInteractionPoint(Paddle paddle){
        // left
        if (getElementAt(x, y + r) == paddle){
            System.out.println("CHECK!!");
            return true;
        }
        // up
        if (getElementAt(x + r, y) == paddle){
            System.out.println("CHECK!!");
            return true;
        }
        // right
        if (getElementAt(x + 2 * r, y + r) == paddle){
            System.out.println("CHECK!!");
            return true;
        }
        // down
        if (getElementAt(x + r, y + 2 * r) == paddle){
            System.out.println("CHECK!!");
            return true;
        }
        return false;
    }

    /**
     * Updates the position of the ball. Gravity assigned.
     */
    public void move() {
        x += dx;
        y += dy;
        this.setCenter(x, y);
        dy -= GRAVITY;
    }

    /**
     * Stops the ball by setting dx and dy to be 0.
     */
    public void stop() {
        dy = 0;
        dy = 0;
        this.setCenter(x, y);
    }

    /**
     * Makes the ball slide horizontally by slowly decreasing dx.
     */
    public void slideHorizontally() {
        dy = 0;
        if (dx > 0) {
            dx -= 0.0001;
        }
        if (dx < 0) {
            dx += 0.0001;
        }
    }

    public double getR() {
        return r;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }
}
