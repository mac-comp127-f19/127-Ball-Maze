package ballMaze;

import ballMaze.Challenges.*;
import comp127graphics.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class crossMaze {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 500;

    private CanvasWindow canvas;
    private Ball ball;
    private List<GraphicsObject> objects = new ArrayList<>();
    private WallManager wallManager;
    private Paddle paddle;
    private int lives = 5;
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
        this.wallManager = new WallManager(10);
        this.ball = new Ball(50, 50, 10);
        this.paddle = new Paddle(400, 200, 55, 20);

        canvas.add(wallManager);
        wallManager.add(generateChallengeGroup());
//        canvas.add(paddle);
//        canvas.draw();
        canvas.animate(() -> {
                    ballmove(ball);
                    animateWallManager();
                    if (loseOnelife()) {
//                        canvas.removeAll();
                        livesLeft.setCenter(ball.getX(), 80);
                        lives--;
                        livesLeft.setText("You still have " + lives + " lives.");
                        canvas.add(livesLeft);
                        operateGame();
                    } else if (lost()) {
                        lostText.setCenter(CANVAS_WIDTH * 0.5, 500);
                        canvas.add(lostText);
                        movementAfterlost(ball);
                    }
                }
        );
        trackMouseMovement();
//        canvas.onMouseMove(event -> paddle.move(event.getPosition()));
    }

    // ---------- backup to paddle ------------
    public void trackMouseMovement(){
        canvas.onClick(event -> {
            Point position = event.getPosition();
            if (Math.abs(position.getX() - ball.getX()) > Math.abs(position.getY() - ball.getY())){
                if (position.getX() > ball.getX()) {
                    ball.setDx(ball.getDx() - 0.2);
                }
                if (position.getX() < ball.getX()){
                    ball.setDx(ball.getDx() + 0.2);
                }
            }
            else{
                if (position.getY() > ball.getY()){
                    ball.setDy(ball.getDy() - 0.5);
                }
                if (position.getY() < ball.getY()){
                    ball.setDy(ball.getDy() + 0.2);
                }
            }
        });
    }

    /**
     * Evaluate the contact between the upper and bottom sides of the paddle
     * and the ball.
     */

    public boolean touchPaddleUD() {
        // up
        if (canvas.getElementAt(ball.getX(), ball.getY() - ball.getR()) == paddle) {
//            ball.setPosition(ball.getX(), paddle.getY() + paddle.getHeight() / 2 + ball.getR());
            return true;
        }
        // down
        if (canvas.getElementAt(ball.getX(), ball.getY() + ball.getR()) == paddle) {
//            ball.setPosition(ball.getX(), paddle.getY() - paddle.getHeight() / 2 - ball.getR());
            return true;
        }
        return false;
    }

    /**
     * Evaluate the contact between the right and left sides of the paddle
     * and the ball.
     */

    public boolean touchPaddleLR() {
        // right
        if (canvas.getElementAt(ball.getX(), ball.getY()) == paddle) {
//            ball.setCenter(paddle.getX() - ball.getR(), ball.getY());
            return true;
        }
        // left
        if (canvas.getElementAt(ball.getX() - ball.getR(), ball.getY()) == paddle) {
            ball.setCenter(paddle.getX() + ball.getR() + paddle.getWidth(), ball.getY());
            return true;
        }
        return false;
    }

    /**
     * Evaluate the contact between the canvas and the ball.
     *
     * @return true if they have contacts with each other.
     */

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

    public boolean touchCanvasTop() {
        if (ball.getY() - ball.getR() < 0) {
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
        canvas.closeWindow();
    }

    private boolean lost() {
        return lives < 1;
    }

    private boolean loseOnelife() {
        return (ball.getY() + ball.getR()) > canvas.getHeight();
    }

    /**
     * Change the motions of the ball by evaluating the different
     * objects and directions that ball makes a contact with.
     */

    private void ballmove(Ball ball) {
        canvas.add(ball);
        ball.move();
        canvas.draw();
        if (touchCanvasBottom()) {
            ball.slideHorizontally();
        }
        if (touchCanvasRight()) {
            ball.setDx(0);
        }
        if (touchCanvasLeft()) {
            ball.setDx(0);
        }
        if (touchWithTopOrBottomOfWall()) {
            ball.setDy(0);
        }
        if (touchWithSidesOfWall()) {
            ball.setDx(0);
        }
        if (touchPaddleUD()) {
            ball.setDy(-ball.getDy());
        }
        if (touchPaddleLR()) {
            ball.setDx(-ball.getDx());
        }
        if (touchCanvasTop()) {
            ball.setDy(-ball.getDy());
        }
    }

    /**
     * Generate different and random challenges shown on the canvas
     */

    public GraphicsGroup generateChallengeGroup(){
        Random random = new Random();
        int n = random.nextInt(6);
        int x = random.nextInt(500);
        while (x < 100) {
            x = random.nextInt(300);
        }
        int y = random.nextInt(400);
        while (y < 100){
            y = random.nextInt(400);
        }
        if (n == 0){
            return new Antigravity(x, y, ball);
        }
        if (n == 1){
            return new Laccelerator(ball, x, y);
        }
        if (n == 2){
            return new Raccelerator(ball, x, y);
        }  if (n == 3){
            return new Uaccelerator(ball, x, y);
        }
        if (n == 4){
            return new Daccelerator(ball, x, y);
        }
        if (n == 5){
            return new Teletrans(ball, x, y);
        }
        return null;
    }

            public static void main(String[] args) {
        new crossMaze();
    }
}
