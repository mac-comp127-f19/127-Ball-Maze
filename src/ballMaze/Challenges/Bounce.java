package ballMaze.Challenges;

import ballMaze.Wall;
import ballMaze.WallManager;

/**
 * Bounce the ball back when it hits a wall
 * by reverting dx or dy.
 */

public class Bounce extends Wall {
    public Bounce(double x, double y, double width, double height) {
        super(x, y, width, height);

    }
}
