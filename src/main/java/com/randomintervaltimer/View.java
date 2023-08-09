package com.randomintervaltimer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

public class View extends Application {
    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);
        
        stage.setTitle("Random Interval Timer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
