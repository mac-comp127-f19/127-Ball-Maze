package ballMaze;

import comp127graphics.GraphicsGroup;

import java.util.*;
import java.util.List;


public class WallManager extends GraphicsGroup {
    private static final double GRID_HEIGHT = 250; // 1/2 of canvas height
    private static final double GRID_WIDTH = 200;
    private static final double VWALL_WIDTH = GRID_WIDTH / 10;
    private static final double VWALL_HEIGHT = GRID_HEIGHT / 2;
    private static final double HWALL_WIDTH = GRID_WIDTH / 2;
    private static final double HWALL_HEIGHT = GRID_HEIGHT / 10;

    private List<Wall> wallList = new ArrayList<>();

    private Map<Integer, Wall> wallCoor = new HashMap<>();

    public WallManager(int n) {
        drawRandomWalls(n);
    }

    /**
     * Draw random walls in fixed grids.
     */

    public void drawRandomWalls(int n) {
        Random random = new Random();

        // generate random walls
        for (int i = 0; i < n; i++) {
            initializeWallCoor(i);
            // create two different random numbers for walls.
            int a = random.nextInt(5);
            int b = random.nextInt(5);
            while (a == b) {
                b = random.nextInt(5);
            }
            Wall wall1 = getSingleRandomWall(a);
            Wall wall2 = getSingleRandomWall(b);
            this.add(wall1);
            this.add(wall2);
            wallList.add(wall1);
            wallList.add(wall2);
            int c = random.nextInt(6);
//            if (c == 3){
//                this.add(ChallengeGroup.generateRandomElement(GRID_WIDTH*n + GRID_WIDTH, GRID_HEIGHT * 1.5));
//            }
//            if (c == 4){
//                this.add(ChallengeGroup.generateRandomElement(GRID_WIDTH*n + GRID_WIDTH, GRID_HEIGHT*0.5));
//            }
        }

    }

    public Wall getSingleRandomWall(int n){
        return wallCoor.get(n);
    }

    /**
     * Initialize the map for the other grids with coordinate (n * w, 0), (n * w, h)
     */

    public void initializeWallCoor(int n) {
        double x = GRID_WIDTH * n;
        // 4 vertical walls set on the right side of the two grids. 2 for each grid.
        wallCoor.put(0, new Wall(x + GRID_WIDTH, GRID_HEIGHT * 0, VWALL_WIDTH, VWALL_HEIGHT));
        wallCoor.put(1, new Wall(x + GRID_WIDTH, GRID_HEIGHT * 0.5, VWALL_WIDTH, VWALL_HEIGHT));
        wallCoor.put(2, new Wall(x + GRID_WIDTH, GRID_HEIGHT * 1, VWALL_WIDTH, VWALL_HEIGHT));
        wallCoor.put(3, new Wall(x + GRID_WIDTH, GRID_HEIGHT * 1.5, VWALL_WIDTH, VWALL_HEIGHT));
        // 2 horizontal walls set on the middle line of two grids.
        wallCoor.put(4, new Wall(x + 0.5 * GRID_WIDTH, GRID_HEIGHT, HWALL_WIDTH, HWALL_HEIGHT));
    }

    public List<Wall> getWallList() {
        return wallList;
    }
}
