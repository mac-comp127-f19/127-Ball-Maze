package ballMaze;

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
    private WallManager wallManager;
    private Paddle paddle;

    public crossMaze() {
        canvas = new CanvasWindow("Ball Maze", CANVAS_WIDTH, CANVAS_HEIGHT);

        this.wallManager = new WallManager(5);

        this.ball = new Ball(100, 300, 10);

        this.paddle = new Paddle(400, 200, 40, 10);

        canvas.add(ball);
        canvas.add(wallManager);
        canvas.draw();

        canvas.add(paddle);
        System.out.println(ball.getR());
        System.out.println(ball.getWidth());
        canvas.animate(this::operateGame);
        canvas.onMouseMove(event -> paddle.move(event.getPosition()));
    }

    /**
     * Evaluates whether the ball is moving or hit with something.
     * If it should stop, then it's dx and dy is set to be 0.
     */
    public void operateGame() {
        ball.move();
        animateWallManager();
        if (touchCanvasBottom()){
            ball.slideHorizontally();
        }
        if (touchCanvasRight()){
            ball.setDx(0);
        }
        if (touchCanvasLeft()){
            ball.setDx(0);
        }
        if (touchWithTopOrBottomOfWall()){
            ball.setDy(0);
        }
        if (touchWithSidesOfWall()){
            ball.setDx(0);
        }
        if (touchPaddleUD()){
            ball.setDy(-ball.getDy());
        }
        if (touchPaddleLR()){
            ball.setDx(-ball.getDx());
        }

        //TODO: touch with canvas.
    }

    public boolean touchPaddleUD(){
        // up
        if (canvas.getElementAt(ball.getX(), ball.getY() - ball.getR()) == paddle){
            ball.setPosition(ball.getX(), paddle.getY() - paddle.getHeight() / 2 - ball.getR());
            return true;
        }
        // down
        if (canvas.getElementAt(ball.getX(), ball.getY() + ball.getR()) == paddle){
            ball.setPosition(ball.getX(), paddle.getY() + paddle.getHeight() / 2 + ball.getR());
            return true;
        }
        return false;
    }

    public boolean touchPaddleLR(){
        // left
        if (canvas.getElementAt(ball.getX() - ball.getR(), ball.getY()) == paddle){
            ball.setPosition(paddle.getX() - ball.getR() - paddle.getWidth()/2, ball.getY());
            return true;
        }
        // right
        if (canvas.getElementAt(ball.getX() + ball.getR(), ball.getY()) == paddle){
            ball.setPosition(paddle.getX() + ball.getR() + paddle.getWidth()/2, ball.getY());
            return true;
        }
        return false;
    }

    public boolean touchCanvasBottom(){
        if (ball.getY() + ball.getR() > canvas.getHeight()){
            return true;
        }
        return false;
    }

    public boolean touchCanvasRight(){
        if (ball.getX() + ball.getR() > canvas.getWidth()){
            return true;
        }
        return false;
    }

    public boolean touchCanvasLeft(){
        if (ball.getX() - ball.getR() < 0){
            return true;
        }
        return false;
    }


    public void animateWallManager() {
        wallManager.setPosition(-ball.getX() - 30, 0);
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

    public static void main(String[] args) {
        new crossMaze();
    }
}
