package ballMaze;

import comp127graphics.CanvasWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class WallManager {
    private static final Color green = new Color(13, 185, 111, 194);
    private CanvasWindow canvas;
    private List<Wall> wallList = new ArrayList<>();
    private float height;
    private float width;

    public WallManager(CanvasWindow canvas){
        this.canvas = canvas;
//        createBottomRMiddleVerticalWall();
//        createBottomRightVerticalWall();
        createRightSideHorizontalWall();
    }

    /**
     * creates a vertical green wall settled at the bottom of the canvas.
     */
    public void createBottomRMiddleVerticalWall(){
        Wall wall = new Wall(650, 200, 20, 800);
        wall.setFillColor(green);
        wall.setStrokeColor(green);
        wallList.add(wall);
        canvas.add(wall);
    }

    /**
     * creates a vertical green wall settled at the bottom of the canvas.
     */
    public void createBottomRightVerticalWall(){
        Wall wall = new Wall(700, 500, 20, 500);
        wall.setFillColor(green);
        wall.setStrokeColor(green);
        wallList.add(wall);
        canvas.add(wall);
    }

    /**
     * creates a horizontal green wall settled at the right side of the canvas.
     */
    public void createRightSideHorizontalWall(){
        Wall wall = new Wall(300, 500, 700, 20);
        wall.setFillColor(green);
        wall.setStrokeColor(green);
        wallList.add(wall);
        canvas.add(wall);
    }


    public List<Wall> getWallList(){
        return wallList;
    }
}
