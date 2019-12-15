package ballMaze;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

import java.awt.*;

/**
 * Creates a ball object. The ball will update its position every second as time goes.
 */
public class Ball extends Ellipse {
    private static final Color darkRed = new Color(48, 19, 5, 255);
    public static final double GRAVITY = -1;

    private double x;
    private double y;
    private double r;
    private double width;
    private double height;
    private double initialxVelocity;
    private double initialyVelocity;



    public Ball(double x, double y, double r, double initialxVelocity, double initialyVelocity) {
        super(x, y, 2 * r, 2 * r);
        this.x = x;
        this.y = y;
        this.r = r;
        this.width = 2 * r;
        height = 2 * r;
        this.initialxVelocity = initialxVelocity;
        this.initialyVelocity = initialyVelocity;

        this.setFillColor(darkRed);
    }

    /**
     * Updates the position of the ball. Gravity assigned.
     */
    public void move(double dt) {
            x += initialxVelocity * dt;
            y += initialyVelocity * dt;
            this.setCenter(x, y);
            initialyVelocity -= GRAVITY * dt;
    }

    /**
     * Stops the ball by setting dx and dy to be 0.
     */
    public void stop(){
        initialyVelocity = 0;
        initialyVelocity = 0;
        this.setCenter(x, y);
    }


    /**
     * Makes the ball slide horizontally by slowly decreasing dx.
     */
    public void slideHorizontally(){

        if (initialxVelocity > 0) {
            initialxVelocity -= 0.5;
        }
        if (initialxVelocity < 0){
            initialxVelocity += 0.5;
        }
        if (initialxVelocity ==0){
            this.stop();
        }
        initialyVelocity = 0;
    }

    public void hit(double direction, double dt){
        if (direction == 1){
            touchVelocityX1();
            x += initialxVelocity * dt;
            y += initialyVelocity * dt;
            initialyVelocity -= GRAVITY * dt;
        }
        if (direction == 2){
            touchVelocityX2();
            x += initialxVelocity * dt;
            y += initialxVelocity * dt;
            initialyVelocity -= GRAVITY * dt;
        }
        if (direction == 3){
            touchVelocityY1();
            x += initialxVelocity * dt;
            y += initialyVelocity * dt;
            initialyVelocity -= GRAVITY * dt;

        }
        if (direction == 4){
            touchVelocityY2();
            x += initialxVelocity * dt;
            y += initialxVelocity * dt;
            initialyVelocity -= GRAVITY * dt;
        }
         this.setCenter(x , y);
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

    public void setDx(double initialxVelocity){ this.initialxVelocity = initialxVelocity; }

    public void setDy(double initialyVelocity){
        this.initialyVelocity = initialyVelocity;
    }

    public double getInitialxVelocity() {return initialxVelocity;}

    public double getInitialyVelocity() {return initialyVelocity;}

    public double touchVelocityX1() {return this.initialxVelocity = initialxVelocity + 5;}

    public double touchVelocityX2() {return this.initialxVelocity = -getInitialxVelocity() - 5;}

    public double touchVelocityY1() {return this.initialyVelocity = initialyVelocity + 5;}

    public double touchVelocityY2() {return this.initialyVelocity = -getInitialyVelocity() - 5;}

    public boolean ballhitthewallX(){
        return x <= 0 || x >= 1000 - width;
    }
    public boolean ballhittheupper(){
        return y <= 0;
    }

    public void hitwall(double dt){

        if (ballhitthewallX()){
            initialxVelocity = -getInitialxVelocity() + 1;
            x += initialxVelocity * dt;
            y += initialyVelocity * dt;
            initialyVelocity -= GRAVITY * dt;
        }
        if (ballhittheupper()){
            touchVelocityY2();
            x += initialxVelocity * dt;
            y += initialyVelocity * dt;
            initialyVelocity -= GRAVITY * dt;
        }
        this.setCenter(x, y);
    }

}
