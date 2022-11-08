// youtube.com/watch?v=ZWpITTZ89mg&t=10s
// JavaFX SketchPad Tutorial

// https://stackoverflow.com/questions/22622034/frosted-glass-effect-in-javafx
// https://stackoverflow.com/questions/34180439/frost-glass-effect-with-dynamic-background-javafx
package com.cute.cutenotes;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class EyeSea extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(410,430);
        Image image = new Image("file:banana.jpg");
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().addAll(imageView);

        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().add(getBox1(canvas));
        vBox.getChildren().add(getBox2(canvas));
        //FXMLLoader fxmlLoader = new FXMLLoader(EyeSea.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(vBox, canvas.getWidth()-10,canvas.getHeight()+30);
        //scene.getStylesheets().addAll(this.getClass().getResource("FrostedGlass.css").toExternalForm());

        stage.setTitle("EyeSea");
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }
    public HBox getBox1(Canvas canvas){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setLineWidth(5);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(e.getX(),e.getY());
            graphicsContext.stroke();
            graphicsContext.closePath();
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            graphicsContext.lineTo(e.getX(),e.getY());
            graphicsContext.stroke();
        });
        HBox hBox = new HBox();
        hBox.getChildren().add(canvas);
        hBox.setStyle("-fx-background-color: black;");
        return hBox;
    }
    public HBox getBox2(Canvas canvas){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        ColorPicker colorPicker= new ColorPicker(Color.BLACK);
        Button button = new Button("Erase");
        button.setOnAction(e-> {
            if(button.getText().equals("Erase"))
            {
                graphicsContext.setStroke(Color.WHITE);
                colorPicker.setVisible(false);
                button.setText("Sketch");
                graphicsContext.setLineWidth(30);
                canvas.setCursor(Cursor.TEXT);
            }
            else
            {
                graphicsContext.setStroke(colorPicker.getValue());
                colorPicker.setVisible(true);
                button.setText("Erase");
                graphicsContext.setLineWidth(5);
                canvas.setCursor(Cursor.DEFAULT);
            }
        });
        colorPicker.setOnAction( e -> graphicsContext.setStroke(colorPicker.getValue()));
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.getChildren().add(canvas);
        hBox.getChildren().add(button);
        hBox.getChildren().add(colorPicker);

        hBox.setStyle("-fx-background-color: white;");
        return hBox;
    }
    public static void main(String[] args) {
        launch();
    }
}