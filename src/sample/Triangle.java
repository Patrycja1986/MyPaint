package sample;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Shape {
    private double x;
    private double y;
    private double width;
    private double height;

    public Triangle(double x, double y, double width, double height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.setStroke(getStrokeColor());
        gc.beginPath();
        gc.moveTo(x,y+height);
        gc.lineTo(x+width,y+height);
        gc.lineTo(x+width/2,y);
        gc.lineTo(x,y+height);
        gc.stroke();
        gc.fill();
        gc.closePath();
    }
}
