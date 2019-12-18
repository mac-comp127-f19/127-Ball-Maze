package ballMaze.Challenges;

import comp127graphics.GraphicsGroup;
import comp127graphics.Image;

/**
 * Enemies wandering in the maze.
 * The user should try avoid these enemies. Otherwise, they lose one live.
 */

public class Enemy extends GraphicsGroup {
    private Image image;
    private double x;
    private double y;

    public Enemy(double x, double y) {
        this.x = x;
        this.y = y;
        image = new Image(x, y);
        image.setImagePath("Mini-Robot.png");
        image.setMaxWidth(100);
        image.setMaxHeight(100);
        image.setPosition(x, y);
        this.add(image);
    }

    public void move(double dx, double dy){
        x += dx;
        y += dy;
        image.setPosition(x, y);
        this.setPosition(x, y);
    }

}
