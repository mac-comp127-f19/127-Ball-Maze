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
    private int lives =  5;
    private GraphicsText lostText = new GraphicsText("Lost feels bad -___-");
    private GraphicsText winText = new GraphicsText("Winner Winner, Wall Dinner !!!");
    private GraphicsText livesLeft = new GraphicsText("You still have " + lives + "lives ");



    public crossMaze() {
        canvas = new CanvasWindow("Ball Maze", CANVAS_WIDTH, CANVAS_HEIGHT);

        operateGame();
    }

    /**
     * Evaluates whether the ball is moving or hit with something.
     * If it should stop, then it's dx and dy is set to be 0.
     */
    public void operateGame() {
        this.wallManager = new WallManager(5);
        this.ball = new Ball(20, 0, 10);
        this.paddle = new Paddle(400, 200, 55, 20);

        canvas.add(wallManager);
        canvas.draw();
        canvas.add(paddle);
        canvas.animate(() -> {
            ballmove(ball);
            if (losingOnelife()) {
                canvas.removeAll();
                livesLeft.setCenter(CANVAS_WIDTH * 0.5, 50);
                lives--;
                livesLeft.setText("You still have " + lives + " lives.");
                canvas.add(livesLeft);
                operateGame();
            }
            else if (lost()){
                lostText.setCenter(CANVAS_WIDTH * 0.5, 500);
                canvas.add(lostText);
                movementAfterlost(ball);
                    }
                }
        );
        ballmove(ball);
        canvas.onMouseMove(event -> paddle.move(event.getPosition()));
        animateWallManager();

        //TODO: touch with canvas.
    }

    public boolean touchPaddleUD(){
        // up
        if (canvas.getElementAt(ball.getX(), ball.getY() - ball.getR()) == paddle){
            ball.setPosition(ball.getX(), paddle.getY() + paddle.getHeight() / 2 + ball.getR());
            return true;
        }
        // down
        if (canvas.getElementAt(ball.getX(), ball.getY() + ball.getR()) == paddle){
            ball.setPosition(ball.getX(), paddle.getY() - paddle.getHeight() / 2 - ball.getR());
            return true;
        }
        return false;
    }

    public boolean touchPaddleLR(){
        // right
        if (canvas.getElementAt(ball.getX() + ball.getR(), ball.getY()) == paddle){
            ball.setCenter(paddle.getX() - ball.getR(), ball.getY());
            return true;
        }
        // left
        if (canvas.getElementAt(ball.getX() - ball.getR(), ball.getY()) == paddle){
            ball.setCenter(paddle.getX() + ball.getR() + paddle.getWidth(), ball.getY());
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

    private void movementAfterlost(Ball ball) {
        canvas.add(ball);
        ball.move();
        canvas.draw();
        canvas.pause(4250);
        canvas.closeWindow();
    }

    private boolean lost() {
        return lives < 1;
    }

    private boolean losingOnelife() {
        return (ball.getY() + ball.getR()) > canvas.getHeight() -10;
    }

    private void ballmove(Ball ball){
        canvas.add(ball);
        ball.move();
        canvas.draw();
        canvas.pause(40);
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
    }

    public static void main(String[] args) {
        new crossMaze();
    }
}
