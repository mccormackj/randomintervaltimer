package com.randomintervaltimer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class View extends Application {
    private Controller controller = new Controller();

    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);
        
        HBox btnBox = new HBox();
        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");
        startBtn.setOnAction(event -> controller.handleStartButtonClick());
        stopBtn.setOnAction(event -> controller.handleStopButtonClick());
        btnBox.getChildren().addAll(startBtn, stopBtn);
        root.getChildren().addAll(btnBox);


        stage.setTitle("Random Interval Timer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
