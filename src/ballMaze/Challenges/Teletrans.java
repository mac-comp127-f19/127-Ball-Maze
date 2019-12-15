package ballMaze.Challenges;

import ballMaze.Ball;
import comp127graphics.*;
import comp127graphics.Point;
import comp127graphics.Rectangle;

import java.awt.*;

/**
 * Teletransportation point in our maze.
 * A pair of spots are generated randomly on the map.
 * When the ball touches one of them,
 * it is teletransported to the other randomly.
 * Two spots are marked with the same color for clarity.
 */

public class Teletrans extends GraphicsGroup{
    private final Color green = new Color(140, 179, 50);
    private final Color grey = new Color(80, 217, 188);

    private GraphicsObject object;
    private Rectangle startPoint;
    private Rectangle endPoint;
    private double xi, yi, xf, yf;
    private double width, height;
    private GraphicsText s; // for Start 起始点
    private GraphicsText f; // for Finish 终点

    public Teletrans(GraphicsObject object){
        double xi = 20;
        double xf = 100;
        double width = 20;
        double height = 20;
        double yi = 15;
        double yf = 100;
        Rectangle startPoint = new Rectangle(xi, yi, width, height);
        startPoint.setFillColor(green);
        Rectangle endPoint = new Rectangle(xf, yf, width, height);
        endPoint.setFillColor(grey);
        GraphicsText s = new GraphicsText("S");
        s.setPosition(xi, yi);
        GraphicsText f = new GraphicsText("F");
        f.setPosition(xf, yf);

        this.add(startPoint);
        this.add(endPoint);
        this.add(s);
        this.add(f);

        this.xi = xi;
        this.yi = yi;
        this.yf = yf;
        this.xf = xf;
        this.width = width;
        this.height = height;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.object = object;
    }

    /**
     * @return true if the object hits the starting point.
     */
    public boolean interactWithBall(){
        Point position = object.getPosition();
            if (getElementAt(position) == startPoint){
                System.out.println("HIT");
            return true;
        }
        return false;
    }

    /**
     * Teletransport the object to the other position.
     */
    public void teletransport(){
        if (interactWithBall()){
            object.setPosition(xf, yf);
            System.out.println("Teletransported");
        }
    }
}
