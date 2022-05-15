package org.example;

import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {


    public void start(Stage stage) {
       Pane pane = new Pane();
        var scene = new Scene(pane, 800, 800);

        Image targetImage = new Image("Cursor.png", 16, 16, false, false);

        scene.setCursor(new ImageCursor(targetImage, 16, 16));

        Game game = new Game();
        pane.getChildren().add(game);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}