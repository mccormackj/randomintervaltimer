package com.randomintervaltimer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Path;
import java.nio.file.Paths;

public class View extends Application {
    private Controller controller = new Controller();
    Image play;
    Image pause;

    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);
        
        HBox btnBox = createButtons();
        root.getChildren().addAll(btnBox);
        stage.setTitle("Random Interval Timer");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createButtons(){
        HBox btnBox = new HBox();
        String dir = System.getProperty("user.dir") + "\\src\\main\\resources";
        play = new Image(dir + "\\play_icon.png", 100, 100, true, true);
        pause = new Image(dir + "\\pause_icon.png", 100, 100, true, true);
        Image stop = new Image(dir + "\\stop_icon.png", 100, 100, true, true);
        ImageView playCtrl = new ImageView(play);
        playCtrl.setOnMouseClicked(e -> {
            controller.handleStartButtonClick();
            playCtrl.setImage(playCtrl.getImage().equals(play) ? pause : play);
            playCtrl.setPickOnBounds(!playCtrl.isPickOnBounds());
        });
        ImageView stopCtrl = new ImageView(stop);
        stopCtrl.setOnMouseClicked(e -> {
            controller.handleStopButtonClick();
            if(playCtrl.getImage().equals(pause)){
                playCtrl.setImage(play);
                playCtrl.setPickOnBounds(!playCtrl.isPickOnBounds());
            }
        });
        btnBox.getChildren().addAll(playCtrl, stopCtrl);
        return btnBox;
    }

    public static void main(String[] args) {
        launch();
    }
}
