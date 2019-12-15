package ballMaze.Challenges;

import ballMaze.Ball;
import comp127graphics.GraphicsGroup;
import comp127graphics.Image;

public class Laccelerator extends GraphicsGroup implements Accelerate {
    private static final double MAXHEIGHT = 40;
    private static final double MAXWIDTH = 150;
    private Ball ball;
    private double x, y;

    public Laccelerator(Ball ball, double x, double y) {
        this.ball = ball;
        this.x = x;
        this.y = y;

        Image image = new Image(x, y);
        image.setMaxHeight(MAXHEIGHT);
        image.setMaxWidth(MAXWIDTH);
        image.setImagePath("accelerator left.png");
        this.add(image);
    }

    @Override
    public boolean inspectBallPosition() {
        if (x < ball.getX() && ball.getX() < x + MAXWIDTH) {
            return y < ball.getY() && ball.getY() < y + MAXHEIGHT;
        }
        return false;
    }

    @Override
    public void accelerate() {
        if (inspectBallPosition()) {
            System.out.println("ACCELERATED!!");
            ball.setDx(ball.getDx() - 0.007);
        }
    }
}
