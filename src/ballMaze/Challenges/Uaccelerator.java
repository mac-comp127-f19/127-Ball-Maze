package ballMaze.Challenges;

import ballMaze.Ball;
import comp127graphics.GraphicsGroup;
import comp127graphics.Image;

/**
 * A region that the ball will experience an upper acceleration.
 */

public class Uaccelerator extends GraphicsGroup implements Accelerate {
    private static final double MAXHEIGHT = 150;
    private static final double MAXWIDTH = 40;
    private Ball ball;
    private double x, y;

    public Uaccelerator(Ball ball, double x, double y) {
        this.ball = ball;
        this.x = x;
        this.y = y;

        comp127graphics.Image image = new Image(x, y);
        image.setMaxHeight(MAXHEIGHT);
        image.setMaxWidth(MAXWIDTH);
        image.setImagePath("accelerator up.png");
        this.add(image);
    }

    @Override
    public boolean enterAccelerationArea() {
        if (x < ball.getX() && ball.getX() < x + MAXWIDTH) {
            return y < ball.getY() && ball.getY() < y + MAXHEIGHT;
        }
        return false;
    }

    @Override
    public void accelerate() {
        if (enterAccelerationArea()) {
            ball.setDy(ball.getDy() - 0.007);
        }
    }
}
