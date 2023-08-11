package com.randomintervaltimer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View extends Application {
    private Controller controller = new Controller();
    ImageView playCtrl;
    Image play;
    Image pause;

    public void start(Stage stage){
        controller.connectView(this);
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
        playCtrl = new ImageView(play);
        playCtrl.setOnMouseClicked(e -> {
            controller.handleStartButtonClick();
        });
        ImageView stopCtrl = new ImageView(stop);
        stopCtrl.setOnMouseClicked(e -> {
            controller.handleStopButtonClick();
        });
        btnBox.getChildren().addAll(playCtrl, stopCtrl);
        return btnBox;
    }

    public void togglePlayImage(){
        playCtrl.setImage(playCtrl.getImage().equals(play) ? pause : play);
        playCtrl.setPickOnBounds(!playCtrl.isPickOnBounds());
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        controller.close();
}
}
