package ballMaze;

import ballMaze.Challenges.Laccelerator;
import comp127graphics.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class crossMaze {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 500;

    private CanvasWindow canvas;
    private Ball ball;
    private List<GraphicsObject> objects = new ArrayList<>();
    private GraphicsText distance = new GraphicsText();
    private WallManager wallManager;
    private Paddle paddle;

    public crossMaze() {
        canvas = new CanvasWindow("Ball Maze", CANVAS_WIDTH, CANVAS_HEIGHT);
        distance.setPosition(700, 470);

        this.ball = new Ball(100, 300, 10);

        this.wallManager = new WallManager(10);

        this.paddle = new Paddle(400, 400, 40, 10);

//        Laccelerator laccelerator = new Laccelerator(ball, 3,3);
        canvas.add(distance);
        canvas.add(ball);
        canvas.add(wallManager);
        canvas.draw();

        canvas.add(paddle);
        canvas.animate(this::operateGame);
        canvas.onMouseMove(event -> paddle.move(event.getPosition()));
    }

    public void updateMaxDistance() {
        double x = 0;
//        while (x < - wallManager.getPosition().getX()) {
//            x = - wallManager.getPosition().getX() - 150;
//        }
        distance.setText("maximum distance traveled so far: " + -wallManager.getPosition().getX());
    }

    /**
     * Handles ball movement, wall movement.
     * Handles the interaction between the ball, the canvas, and the paddle.
     */
    public void operateGame() {
        ball.move();
        animateWallManager();
        if (touchCanvasBottom()) {
            ball.slideHorizontally();
        }
        if (touchCanvasRight()) {
            ball.setDx(0);
        }
        if (touchCanvasLeft()) {
            ball.setDx(0);
        }
        if (touchWithTopOfWall()) {
            ball.setDy(0);
        }
        if (touchWithSidesOfWall()) {
            ball.setDx(0);
        }
        if (collideWithPaddleUp()){
            ball.setDy(-ball.getDy() + 0.05);
        }
//        ball.collideWithPaddle(paddle);
        touchWithBottomOfWall();
    }

    public boolean collideWithPaddleUp(){
        // up
        if (canvas.getElementAt(ball.getX(), ball.getY() + ball.getR()) == paddle){
                System.out.println(ball.getX() +" " + ball.getY()+ball.getR());
                System.out.println("paddle height " + paddle.getTopY());
            ball.setPosition(ball.getX(), 395 - ball.getR());
                System.out.println("set position to " +ball.getPosition());
            return true;
        }
        return false;
    }

    public boolean touchCanvasBottom() {
        if (ball.getY() + ball.getR() > canvas.getHeight()) {
            return true;
        }
        return false;
    }

    public boolean touchCanvasRight() {
        if (ball.getX() + ball.getR() > canvas.getWidth()) {
            return true;
        }
        return false;
    }

    public boolean touchCanvasLeft() {
        if (ball.getX() - ball.getR() < 0) {
            return true;
        }
        return false;
    }


    public void animateWallManager() {
        wallManager.setPosition(-ball.getX() - 30, 0);
//        updateMaxDistance();
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
    public boolean touchWithTopOfWall() {
        Point up = new Point(ball.getX(), ball.getY() - ball.getR());
//        Point down = new Point(ball.getX(), ball.getY() + ball.getR());
//        List<Point> points = new ArrayList<>(Arrays.asList(up, down));

//        for (Point point : points) {
            for (Wall i : wallManager.getWallList()) {
                if (canvas.getElementAt(up) == i) {
                    return true;
                }
//            }
        }
        return false;
    }

    public void touchWithBottomOfWall(){
        for (Wall i : wallManager.getWallList()){
            if (canvas.getElementAt(ball.getX(), ball.getY() + ball.getR()) == i){
                ball.setDy(-ball.getDy());
            }
        }
    }

    public static void main(String[] args) {
        new crossMaze();
    }
}
