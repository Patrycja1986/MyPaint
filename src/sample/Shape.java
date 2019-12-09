package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

abstract class Shape {
    private Color strokeColor;
    private Color fillColor;
    private ColorPicker colorPicker;




    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public ColorPicker getColor() {
        return colorPicker;
    }

    public void setColor(ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }

    public abstract void draw(GraphicsContext gc);

}
