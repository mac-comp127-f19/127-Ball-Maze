package ballMaze;

import comp127graphics.Rectangle;

public class Hitter extends Rectangle{
    private double x;
    private double y;
    private double width;
    private double height;

    public Hitter(double x, double y, double width, double height){
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getx() {return x + width;};

    public double gety() {return y + height/2;}

//    public changedirection ()
}
