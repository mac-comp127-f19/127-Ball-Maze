package ballMaze.Challenges;

import ballMaze.Ball;
import comp127graphics.GraphicsGroup;
import comp127graphics.Image;

/**
 * An acceleration in the downward direction.
 */

public class Daccelerator extends GraphicsGroup implements Accelerate{
    private static final double MAXHEIGHT = 150;
    private static final double MAXWIDTH = 40;
    private Ball ball;
    private double x, y;

    public Daccelerator(Ball ball, double x, double y) {
        this.ball = ball;
        this.x = x;
        this.y = y;

        Image image = new Image(x, y);
        image.setMaxHeight(MAXHEIGHT);
        image.setMaxWidth(MAXWIDTH);
        image.setImagePath("accelerator down.png");
        this.add(image);
    }

    /**
     * Loops over all the points in the acceleration area to see if there is an object belongs to Ball class.
     * @return true if there is. Otherwise false.
     */
    public boolean enterAccelerationArea() {
        if (x < ball.getX() && ball.getX() < x + MAXWIDTH) {
            return y < ball.getY() && ball.getY() < y + MAXHEIGHT;
        }
        return false;
    }

    /**
     * Sets the velocity of the ball to be twice of its initial velocity.
     */
    public void accelerate(){
        if (enterAccelerationArea()) {
            System.out.println("ACCELERATED!!");
            ball.setDy(ball.getDy() + 0.007);
        }
    }
}
