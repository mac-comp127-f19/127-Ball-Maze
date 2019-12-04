package ballMaze;

import comp127graphics.*;

import java.awt.Graphics2D;
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

    public crossMaze(){

        canvas = new CanvasWindow("RotatedMaze", CANVAS_WIDTH, CANVAS_HEIGHT);
        gameoperation();

    }

    public void gameoperation(){
        this.ball = new Ball(500, 300, 10, canvas);
//        System.out.println(ball.getPosition()); // upper left corner.
//        System.out.println(ball.getCenter()); // the REAL center, not upper left corner.
//        System.out.println(ball.getX()); // centerX
//        System.out.println(ball.getR());
//        System.out.println("lower right corner is: " + (ball.getR() + ball.getX()));

        canvas.add(ball);
        canvas.animate(() -> {gameIsRunning();
                    System.out.println(ball.getPosition());}
//                this::gameIsRunning
        );
        this.wallManager = new WallManager(canvas);
        wallManager.createWall();
    }

    /**
     * Evaluates whether the ball is moving or hit with something.
     * If it should stop, then it's dx and dy is set to be 0.
     */
    public void gameIsRunning(){
        /* TODO: make the ball slide according to different situations, i.e.,
            sliding on the wall or sliding on the wall of the canvas. */
        ball.move();
        if (!touchWithWall()){
            ball.stop();
        }
//        ball.move();
    }

    /**
     * Evaluate four midpoints of the ball to see whether they touch with a wall.
     */
    public boolean touchWithWall(){
        Point up = new Point(ball.getX(), ball.getY() - ball.getR());
        Point down = new Point(ball.getX(), ball.getY() + ball.getR());
        Point left = new Point(ball.getX() -  ball.getR(), ball.getY());
        Point right = new Point(ball.getX() + ball.getR(), ball.getY());
        List<Point> points = new ArrayList<>(Arrays.asList(up, down, left, right));

        for (Point point : points) {
            for (Wall i : wallManager.getWallList()){
                if (canvas.getElementAt(point) == i) {
                    System.out.println(point);
                    System.out.println(i);
                    ball.stop();
                    // TODO: change dx and dy to a reasonable value so that the ball could slide on the wall.
                    return false;
                }
        }}
        return true;
    }







    public void ballmovement(){
        canvas.onDrag(i -> {
            {ball.setCenter(currentpositionX = i.getPosition().getX(),
                    currentpositionY = i.getPosition().getY());
            this.currentpositionX = ball.getX();
            this.currentpositionY = ball.getY();
            System.out.println(ball.getX() + "   " + ball.getY());}
        });

        if (!judgetouchment()){
            canvas.closeWindow();
        }

    }

    public boolean judgetouchment(){
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
        for (GraphicsObject object : objects){
            if (object instanceof Wall){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        new crossMaze();

    }
}
