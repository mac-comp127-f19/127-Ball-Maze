package ballMaze;

import comp127graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;


public class WallManager {
    private CanvasWindow canvas;
    private List<Wall> wallList = new ArrayList<>();

    public WallManager(CanvasWindow canvas){
        this.canvas = canvas;
    }

    public void createWall(){
        Wall wall = new Wall(canvas.getWidth()/2, canvas.getHeight(), canvas.getWidth()/2, canvas.getHeight()*2/3);
        wallList.add(wall);
    }
}
