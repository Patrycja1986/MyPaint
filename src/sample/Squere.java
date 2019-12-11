package sample;

import javafx.scene.canvas.GraphicsContext;

public class Squere extends Shape {

    private double x;
    private double y;
    private double side;

    public Squere(double x, double y, double width, double height){
        super();
        side= Math.min(width,height);
        this.x=x;
        this.y=y;

    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillRect(x,y,side,side);
    }

}
