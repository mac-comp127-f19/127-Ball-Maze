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
    private Rectangle demoMaze;
    private GraphicsText lostText = new GraphicsText("Game Over!!!");
    private List<GraphicsObject> objects = new ArrayList<>();
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 1000;
    private double currentpositionX;
    private double currentpositionY;

    public crossMaze(){

        canvas = new CanvasWindow("RotatedMaze", CANVAS_WIDTH, CANVAS_HEIGHT);
        gameoperation();

    }

    public void gameoperation(){

        this.ball = new Ball(500, 300, 20, 20);
        canvas.add(ball);
        this.demoMaze = new Maze(300, 200, 400, 300);
        canvas.add(demoMaze);
        ballmovement();
    }

    public void ballmovement(){
        canvas.onDrag(i -> {
            {ball.setCenter(currentpositionX = i.getPosition().getX(),
                    currentpositionY = i.getPosition().getY());}
        });

        this.currentpositionX = ball.getX();
        this.currentpositionY = ball.getY();
        judgetouchment();

    }

    public boolean judgetouchment(){
        GraphicsObject object = canvas.getElementAt(
                currentpositionX + ball.getWidth(), currentpositionY);
        objects.add(object);
        System.out.println(objects);
        for (GraphicsObject maze : objects){
            if (maze instanceof Maze){
                canvas.closeWindow();
            }
        }
        return false;
    }


    public static void main(String[] args) {

        new crossMaze();

    }
}
