package ballMaze;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsObject;
import comp127graphics.GraphicsText;
import comp127graphics.Rectangle;
import java.awt.Graphics2D;
import java.util.ArrayList;
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

        this.ball = new Ball(500, 300, 20, canvas);
        canvas.add(ball);
        canvas.animate(() -> {ball.move();});
        this.wallManager = new WallManager(canvas);
        wallManager.createWall();
//        this.demoMaze = new Maze(300, 200, 400, 300);
//        canvas.add(demoMaze);
//        ballmovement();
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
