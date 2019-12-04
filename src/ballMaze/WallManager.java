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
    }

    public void createWall(){
        Wall wall = new Wall(500, 700, 20, 300);
        wall.setFillColor(green);
        wall.setStrokeColor(green);
        wallList.add(wall);
        canvas.add(wall);
    }

    public List<Wall> getWallList(){
        return wallList;
    }
}
