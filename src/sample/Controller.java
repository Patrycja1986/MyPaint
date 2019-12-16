package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller {

    @FXML
    AnchorPane anchorPane;
    @FXML
    Button rubber;
    @FXML
    Button clear;
    @FXML
    ColorPicker colorPicker;
    @FXML
    TextField sizeSetter;
    @FXML
    Canvas canvas;
    @FXML
    Button triangle;
    @FXML
    Button circle;
    @FXML
    Button rectangle;

    private Tool currentTool;

    private double startX;
    private double startY;
    private double draggedX;
    private double draggedY;
    private double width;
    private double height;

    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        anchorPane.setStyle("-fx-background-color: white");
        canvas.setOnMousePressed(mouseEvent -> {
            startX = mouseEvent.getX();
            startY = mouseEvent.getY();
        });
        canvas.setOnMouseDragged(mouseEvent -> {
            draggedX = mouseEvent.getX();
            draggedY = mouseEvent.getY();
            drawPickedValueInColor();
            refresh(gc);
            drawPickedValueInColor();
        });
    }

    @FXML
    public void handleRubberPressed(ActionEvent actionEvent) {
        colorPicker.setValue(Color.WHITE);
        rubber.setOnMouseDragged(mouseEvent -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(colorPicker.getValue());
            gc.fillOval(startX, startY, width, height);
        });
    }

    @FXML
    public void handleClearPressed() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
    }

    @FXML
    public void handleRectanglePressed() {
        currentTool = Tool.RECTANGLE;
    }

    @FXML
    public void handleBrushPressed() {
        currentTool = Tool.BRUSH;
    }

    @FXML
    public void handleSquerePressed() {
        currentTool = Tool.SQUERE;
    }

    @FXML
    private void drawPickedValueInColor() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Shape shape = createShape();
        shape.setFillColor(colorPicker.getValue());
        shape.draw(gc);
        System.out.println(colorPicker.getValue());
    }

    @FXML
    private Shape createShape() {

        double x = Math.min(startX, draggedX);
        double y = Math.min(startY, draggedY);

        double width = Math.abs(draggedX - startX);
        double height = Math.abs(draggedY - startY);


        switch (currentTool) {
            case RECTANGLE:
                return new Rectangle(x,y,width,height);
            case BRUSH:
                double choosedWidth = Double.parseDouble(sizeSetter.getText());
                double choosedHeight = Double.parseDouble(sizeSetter.getText());
                return new Brush(draggedX, draggedY, choosedWidth, choosedHeight);
            case SQUERE:
                return new Squere(x, y, width, height);

        }
        return null;
    }
    private void refresh(GraphicsContext gc){
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
}
