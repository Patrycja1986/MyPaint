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
    Button brush;
    @FXML
    Button triangle;
    @FXML
    Button circle;
    @FXML
    Button rectangle;

    Tool currentTool;
    Shape shape;

    double startX;
    double startY;
    double draggedX;
    double draggedY;
    double endX;
    double endY;
    double width;
    double height;


    public void initialize() {

        anchorPane.setStyle("-fx-background-color: white");
        canvas.setOnMousePressed(mouseEvent -> {
            startX = mouseEvent.getX();
            startY = mouseEvent.getY();
        });
        canvas.setOnMouseDragged(mouseEvent -> {
            endX = mouseEvent.getX();
            endY= mouseEvent.getY();
            drawPickedValueInColor();
        });
        canvas.setOnMouseReleased(mouseEvent -> {
            endX = mouseEvent.getX();
            endY = mouseEvent.getY();
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
    public void handleClearPressed(ActionEvent actionEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
    }

    @FXML
    public void handleRectanglePressed(ActionEvent actionEvent) {
        currentTool = Tool.RECTANGLE;
    }
    @FXML
    public void handleBrushPressed(ActionEvent actionEvent){
        currentTool=Tool.BRUSH;
    }
    @FXML
    public void handleSquerePressed(ActionEvent actionEvent){currentTool=Tool.SQUERE;}

    @FXML
    public Shape drawPickedValueInColor(){
        GraphicsContext gc= canvas.getGraphicsContext2D();
        Shape shape= createShape();
        shape.setFillColor(colorPicker.getValue());
        shape.draw(gc);
        System.out.println(colorPicker.getValue());
        return shape;
    }

    @FXML
    public Shape createShape() {

       double x = Math.min(startX, endX);
       double y = Math.min(startY, endY);
        double width = Math.abs(endX - startX);
        double height = Math.abs(endY - startY);

        switch (currentTool) {
            case RECTANGLE:
                System.out.println("rysuję prostokąt");
                return new Rectangle(x,y,width, height);
            case BRUSH:
                double choosedWidth=Double.parseDouble(sizeSetter.getText());
                double choosedHeight=Double.parseDouble(sizeSetter.getText());
                System.out.println("rysuję co mi się podoba");
                 return new Brush(x,y,choosedWidth,choosedHeight);
            case SQUERE:
                System.out.println("rysuję kwadrat");
                return new Squere(x,y,width,height);

        }
        return null;
    }
}
