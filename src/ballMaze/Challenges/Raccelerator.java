package ballMaze.Challenges;

import ballMaze.Ball;
import comp127graphics.GraphicsGroup;
import comp127graphics.Image;

/**
 * A region that the ball will experience an acceleration.
 */
public class Raccelerator extends GraphicsGroup implements Accelerate{
    private static final double MAXHEIGHT = 40;
    private static final double MAXWIDTH = 150;
    private Ball ball;
    private double x, y;

    public Raccelerator(Ball ball, double x, double y){
        this.ball = ball;
        this.x = x;
        this.y = y;

        // Acceleration Area image
        Image image = new Image(x, y);
        image.setMaxHeight(MAXHEIGHT);
        image.setMaxWidth(MAXWIDTH);
        image.setImagePath("accelerator.png");
        this.add(image);
    }

    /**
     * Loops over all the points in the acceleration area to see if there is an object belongs to Ball class.
     * @return true if there is. Otherwise false.
     */
    public boolean inspectBallPosition() {
        if (x < ball.getX() && ball.getX() < x + MAXWIDTH) {
            return y < ball.getY() && ball.getY() < y + MAXHEIGHT;
        }
        return false;
    }

    /**
     * Sets the velocity of the ball to be twice of its initial velocity.
     */
    public void accelerate(){
        if (inspectBallPosition()) {
            System.out.println("ACCELERATED!!");
            ball.setDx(ball.getDx() + 0.007);
        }
    }
}
