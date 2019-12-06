package ballMaze;

import comp127graphics.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class crossMaze {

    private CanvasWindow canvas;
    private Ball ball;
    private Maze demoMaze;
    private GraphicsText lostText = new GraphicsText("Game Over!!!");
    private List<GraphicsObject> objects = new ArrayList<>();
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 1000;
    private double currentpositionX;
    private double currentpositionY;
    private WallManager wallManager;
    private Paddle paddle;

    public crossMaze() {
        canvas = new CanvasWindow("Ball Maze", CANVAS_WIDTH, CANVAS_HEIGHT);

        this.wallManager = new WallManager(canvas);

        this.ball = new Ball(500, 300, 10);

        this.paddle = new Paddle(400, 200, 40, 10);

        canvas.draw();
        canvas.add(ball);
        canvas.add(paddle);
        canvas.animate(() -> operateGame());
        canvas.onMouseMove(event -> paddle.move(event.getPosition()));
    }

    /**
     * Evaluates whether the ball is moving or hit with something.
     * If it should stop, then it's dx and dy is set to be 0.
     */
    public void operateGame() {
        ball.move();
//        paddleMove();
        if (touchWithSidesOfWall()) {
            ball.setDx(0);
        }
        if (touchWithTopOrBottomOfWall()) {
            ball.slideHorizontally();
        }
        if (touchWithCanvas()) {
            ball.slideHorizontally();
        }

    }

    /**
     * Evaluates two side midpoints of the ball
     * to see whether they touch with a wall either on the left or on the right.
     *
     * @return true if the ball touches the wall.
     */
    public boolean touchWithSidesOfWall() {
        Point left = new Point(ball.getX() - ball.getR(), ball.getY());
        Point right = new Point(ball.getX() + ball.getR(), ball.getY());
        List<Point> points = new ArrayList<>(Arrays.asList(left, right));

        for (Point point : points) {
            for (Wall i : wallManager.getWallList()) {
                if (canvas.getElementAt(point) == i) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Evaluates the top and bottom midpoints of the ball
     * to see whether they touch with a wall above or below.
     *
     * @return true if the ball touches the wall.
     */
    public boolean touchWithTopOrBottomOfWall() {
        Point up = new Point(ball.getX(), ball.getY() - ball.getR());
        Point down = new Point(ball.getX(), ball.getY() + ball.getR());
        List<Point> points = new ArrayList<>(Arrays.asList(up, down));

        for (Point point : points) {
            for (Wall i : wallManager.getWallList()) {
                if (canvas.getElementAt(point) == i) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Evaluates the four midpoints of the ball to see whether they touch with the canvas.
     *
     * @return true if the ball touches the canvas.
     */
    public boolean touchWithCanvas() {
        if (ball.getX() - ball.getR() < 0 ||
                ball.getX() + ball.getR() > canvas.getWidth()) {
            ball.setDx(0);
            return true;
        }
        if (ball.getY() - ball.getR() < 0 ||
                ball.getY() + ball.getR() > canvas.getHeight()) {
            ball.setDy(0);
            return true;
        }
        return false;
    }

    public boolean touchWithPaddle() {
        Point touch = new Point(paddle.getx(), paddle.gety());
        if (canvas.getElementAt(touch) == ball) {
            return true;
        }
        return false;
    }

//    public void paddleMove() {
//        canvas.onMouseMove(i -> {
//            paddle.move(i.getPosition());
//        });
//    }


    public static void main(String[] args) {
        new crossMaze();
    }


    // --------------other methods that're not implemented to some reason-------------------

    /**
     * Evaluates four midpoints of the ball to see whether they touch with a wall.
     *
     * @return true if the ball touches the wall.
     */
    public boolean touchWithWall() {
        Point up = new Point(ball.getX(), ball.getY() - ball.getR());
        Point down = new Point(ball.getX(), ball.getY() + ball.getR());
        Point left = new Point(ball.getX() - ball.getR(), ball.getY());
        Point right = new Point(ball.getX() + ball.getR(), ball.getY());
        List<Point> points = new ArrayList<>(Arrays.asList(up, down, left, right));

        for (Point point : points) {
            for (Wall i : wallManager.getWallList()) {
                if (canvas.getElementAt(point) == i) {
                    ball.stop();
                    return true;
                }
            }
        }
        return false;
    }

    public void ballmovement() {
        canvas.onDrag(i -> {
            {
                ball.setCenter(currentpositionX = i.getPosition().getX(),
                        currentpositionY = i.getPosition().getY());
                this.currentpositionX = ball.getX();
                this.currentpositionY = ball.getY();
                System.out.println(ball.getX() + "   " + ball.getY());
            }
        });
        if (!judgetouchment()) {
            canvas.closeWindow();
        }
    }

    public boolean judgetouchment() {
        GraphicsObject object1 = canvas.getElementAt(
                currentpositionX + ball.getWidth(), currentpositionY);
        GraphicsObject object2 = canvas.getElementAt(
                currentpositionX, currentpositionY);
        GraphicsObject object3 = canvas.getElementAt(
                currentpositionX, currentpositionY + ball.getHeight());
        GraphicsObject object4 = canvas.getElementAt(
                currentpositionX + ball.getWidth(), currentpositionY + ball.getHeight());
        objects.add(object1);
        objects.add(object2);
        objects.add(object3);
        objects.add(object4);
        System.out.println(objects);
        for (GraphicsObject object : objects) {
            if (object instanceof Wall) {
                return false;
            }
        }
        return true;
    }

}
