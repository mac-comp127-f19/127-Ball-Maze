package ballMaze;

import ballMaze.Challenges.Uaccelerator;
import comp127graphics.*;
import comp127graphics.Point;

import java.awt.*;
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
    private static final Color color1 = new Color(0x0498F9);


    public crossMaze() {
        canvas = new CanvasWindow("Ball Maze", CANVAS_WIDTH, CANVAS_HEIGHT);

        this.wallManager = new WallManager(canvas);

        this.ball = new Ball(100, 0, 10, 10, 10);

        this.paddle = new Paddle(400, 200, 50, 50);

        System.out.println(ball.getX() + " " + ball.getY());
        System.out.println(paddle.getX() + " " + paddle.getY());

        canvas.draw();
        canvas.add(ball);
        canvas.add(paddle);
        canvas.animate(() -> operateGame());
        canvas.onMouseMove(event -> paddle.move(event.getPosition()));
    }

    public void ballTest() {
        // ---------------- Test for teletransportation -------------------
//        canvas.add(teletrans);
//        canvas.onDrag(event -> ball.setPosition(event.getPosition()));
//        canvas.animate(() -> teletrans.teletransport());

        // ---------------- Test for Accelerator ----------------------
//        Accelerator accelerator = new Accelerator(ball, 120, 120);
//        canvas.add(accelerator);
//        canvas.animate(() -> {
//            ball.move();
//            accelerator.accelerate();
//        });

//        Daccelerator daccelerate = new Daccelerator(ball, 120, 120);
//        canvas.add(daccelerate);
//        canvas.animate(() -> {
//            ball.move();
//            daccelerate.accelerate();
//        });

        Uaccelerator uaccelerator = new Uaccelerator(ball, 120, 120);
        canvas.add(uaccelerator);
        canvas.animate(() -> {
            ball.move(0.1);
            uaccelerator.accelerate();
        });
    }

    public boolean win() {
        double y = Math.random() * (1001);
        double x = (Math.random() * (200)) + 800;
        Wall winarea = new Wall(x, y, 100, 100);
        winarea.setFillColor(color1);
        return ball.getX() >= x || ball.getX() <= x + 100
                || ball.getY() >= y || ball.getY() <= y + 100;
    }

    private void movementAfterlost(Ball ball) {
        canvas.add(ball);
        canvas.draw();
        canvas.pause(4250);
        canvas.closeWindow();
    }

    /**
     * Evaluates whether the ball is moving or hit with something.
     * If it should stop, then it's dx and dy is set to be 0.
     */
    public void operateGame() {
        ball.move(0.1);
//        ball.hitwall(0.1);
        if (touchWithSidesOfWall()){
            ball.setDx(0);
        }
        if (touchWithCanvas()) {
            ball.slideHorizontally();
        }
        if (touchWithTopOrBottomOfWall()){
            ball.slideHorizontally();
        }
        if (touchWithPaddleFront(ball)){
            ball.hit(1, 0.1);
            ball.setCenter(paddle.getX() + paddle.getWidth() + ball.getWidth()/2,
                   ball.getY() - ball.getHeight()/2);
        }
        if (touchWithPaddleUp(ball)){
            ball.hit(3, 0.1);
            ball.setCenter(ball.getX() - ball.getWidth()/2.,
                    paddle.getY() - ball.getHeight()/2);
        }
        if (touchWithPaddleBack(ball)){
            ball.hit(2, 0.1);
            ball.setCenter(paddle.getX() - ball.getWidth()/2,
                    ball.getY() - ball.getHeight()/2);
        }
        if (touchWithPaddleDown(ball)){
            ball.hit(4, 0.1);
            ball.setCenter(ball.getX() - ball.getWidth()/2,
                    paddle.getY() + paddle.getHeight() + ball.getHeight()/2);
        }


    }

    /**
     * Evaluates two side midpoints of the ball
     * to see whether they touch with a wall either on the left or on the right.
     *
     * @return true if the ball touches the wall.
     */
    public boolean touchWithSidesOfWall() {
        Point left = new Point(ball.getX() - 2 * ball.getR(), ball.getY() - ball.getR());
        Point right = new Point(ball.getX(), ball.getY() - ball.getR());
        List<Point> points = new ArrayList<>(Arrays.asList(left, right));

        for (Point point : points) {
            for (Wall i : wallManager.getWallList()) {
                if (canvas.getElementAt(point) == i){
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
        Point down = new Point(ball.getX() - ball.getR(), ball.getY());
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

    public boolean touchWithPaddleFront(Ball ball){
        GraphicsObject obj = canvas.getElementAt(
                ball.getX() - ball.getWidth() , ball.getY() - ball.getHeight() / 2);
        return obj instanceof Paddle;
    }

    public boolean touchWithPaddleUp(Ball ball) {
        GraphicsObject obj = canvas.getElementAt(
                ball.getX() - ball.getWidth() / 2, ball.getY()+ball.getWidth());
        return obj instanceof Paddle;
    }

    public boolean touchWithPaddleDown(Ball ball) {
        GraphicsObject obj = canvas.getElementAt(
                ball.getX() - ball.getWidth() / 2, ball.getY() );
        return obj instanceof Paddle;
    }

    public boolean touchWithPaddleBack(Ball ball) {
        GraphicsObject obj = canvas.getElementAt(
                ball.getX() , ball.getY()- ball.getHeight() / 2);
        return obj instanceof Paddle;
    }

    public static void main(String[] args) {
        new crossMaze();
    }

}
