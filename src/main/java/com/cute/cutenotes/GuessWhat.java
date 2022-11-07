// youtube.com/watch?v=ZWpITTZ89mg&t=10s
// JavaFX SketchPad Tutorial
package com.cute.cutenotes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
//import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class GuessWhat extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Canvas canvas = new Canvas(410,400);
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: white;");
        vbox.getChildren().get(getBox1(canvas));
        vbox.getChildren().get(getBox2(canvas));
        FXMLLoader fxmlLoader = new FXMLLoader(GuessWhat.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
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
        HBox hBox = new HBox();
        hBox.getChildren().add(canvas);
        
        return null;
    }
    public static void main(String[] args) {
        launch();
    }
}