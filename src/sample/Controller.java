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

public class Controller {

    @FXML
    AnchorPane anchorPane;
    @FXML
    Button brush;
    @FXML
    Button clear;
    @FXML
    ColorPicker colorPicker;
    @FXML
    TextField sizeSetter;
    @FXML
    Canvas canvas;
    @FXML
    Button paint;
    @FXML
    Button triangle;
    @FXML
    Button circle;
    @FXML
    Button rectangle;

    Tool currentTool;

    double startX;
    double startY;
    double endX;
    double endY;
    double width;
    double height;


    public void initialize() {

        anchorPane.setStyle("-fx-background-color: white");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(mouseEvent -> {
            startX = mouseEvent.getX();
            startY = mouseEvent.getY();
            System.out.println("Pressed" + startX + " " + startY);
        });

        canvas.setOnMouseDragged(mouseEvent -> {
            startX = mouseEvent.getX();
            startY = mouseEvent.getY();
            width = Double.parseDouble(sizeSetter.getText());
            height = Double.parseDouble(sizeSetter.getText());
            System.out.println("Dragged" + startX + "" + startY);

            gc.setFill(colorPicker.getValue());
            gc.fillOval(startX, startY, width, height);

        });
        canvas.setOnMouseReleased(mouseEvent -> {
            endX = mouseEvent.getX();
            endY = mouseEvent.getY();
            System.out.println("Released" + endX + " " + endY);
        });
    }

    @FXML
    public void handleBrushPressed(ActionEvent actionEvent) {
        colorPicker.setValue(Color.WHITE);
        brush.setOnMouseDragged(mouseEvent -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(colorPicker.getValue());
            gc.fillOval(startX, startY, width, height);
        });
    }

    @FXML
    public void handleClearPressed(ActionEvent actionEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
    }

    @FXML
    public void handleRectanglePressed(ActionEvent actionEvent) {
        //currentTool = Tool.RECTANGLE;


    }

    @FXML
    public Shape createShape() {
        double x = Math.min(startX, endX);
        double y = Math.min(endX, endY);
        double width = Math.abs(endX - startX);
        double height = Math.abs(endY - startY);

        switch (currentTool) {
            case RECTANGLE:
                System.out.println("rysuję prostokąt");
                return new Rectangle(x, y, width, height);
        }
        return null;
    }
}
