package ballMaze.Challenges;

import ballMaze.Ball;
import comp127graphics.GraphicsGroup;
import comp127graphics.Image;

/**
 * The region where the ball experiences anti-gravity.
 */

public class Antigravity extends GraphicsGroup {
    private double width;
    private double height;
    private double x;
    private double y;
    private Ball ball;

    public Antigravity(double x, double y, Ball ball) {
        width = 100;
        height = width;
        this.x = x;
        this.y = y;
        Image image = new Image(x, y);
        image.setImagePath("antigravity.png");
        image.setMaxWidth(width);
        image.setMaxHeight(height);
        this.add(image);
    }

    public boolean enterAntigravityArea(Ball ball) {
        if (x < ball.getX() && ball.getX() < x + width) {
            return y < ball.getY() && ball.getY() < y + height;
        }
        return false;
    }

    public void enableAntigravity(Ball ball) {
        if (enterAntigravityArea(ball)) {
            ball.setDy(-ball.getDy());
        }
    }
}

