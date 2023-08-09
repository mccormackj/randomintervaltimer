package com.randomintervaltimer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class View extends Application {
    private Controller controller = new Controller();

    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);
        
        Button startBtn = new Button("Start Work");
        startBtn.setOnAction(event -> controller.handleButtonClick());
        root.getChildren().add(startBtn);


        stage.setTitle("Random Interval Timer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
