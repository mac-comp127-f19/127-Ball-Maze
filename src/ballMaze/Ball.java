package ballMaze;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

import java.awt.*;

/**
 * Creates a ball object. The ball will update its position every second as time goes.
 */
public class Ball extends Ellipse {
    private static final Color darkRed = new Color(48, 19, 5, 255);
    public static final double GRAVITY = -0.005;

    private double x;
    private double y;
    private double r;
    private double width;
    private double height;
    private double dx;
    private double dy;



    public Ball(double x, double y, double r) {
        super(x, y, 2 * r, 2 * r);
        this.x = x;
        this.y = y;
        this.r = r;
        width = 2 * r;
        height = 2 * r;
        dx = 0.5;
        dy = -0.5;

        this.setFillColor(darkRed);
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
    public void stop(){
        dx = 0;
        dy = 0;
        this.setCenter(x, y);
    }

    /**
     * Makes the ball slide horizontally by slowly decreasing dx.
     */
    public void slideHorizontally(){
        dy = 0;
        if (dx > 0) {
            dx -= 0.001;
        }
        if (dx < 0){
            dx += 0.001;
        }
        if (dx ==0){
            this.stop();
        }
    }

    public void speedafterhitting(double direction){
        if (direction == 1){
            x += dx;
            y += dy;
            this.setCenter(x, y);
            y -= GRAVITY;
        }
        else if (direction == 2){
            x -= dx;
            y += dy;
            this.setCenter(x, y);
            y -= GRAVITY;

        }
        else if (direction == 3){
            y += dy;
            this.setCenter(x, y);
            y += GRAVITY;
        }
        else if (direction == 4){
            y += dy;
            this.setCenter(x, y);
            y -= GRAVITY;
        }


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

    public void setDx(double dx){
        this.dx = dx;
    }

    public void setDy(double dy){
        this.dy = dy;
    }

}
