package com.randomintervaltimer;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Pos;

public class View extends Application {
    private Controller controller = new Controller();
    ImageView playCtrl;
    Image play;
    Image pause;
    Label task;
    List<Font> fonts;

    public void start(Stage stage){
        controller.connectView(this);
        loadAssets();

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 400, 300);
        task = createFormattedTask();
        task.setMinWidth(scene.getWidth());
        task.setMinHeight(scene.getHeight()/10);
        GridPane.setHgrow(task, Priority.ALWAYS);
        GridPane.setVgrow(task, Priority.SOMETIMES);

        HBox btnBox = createButtons();
        btnBox.setMinWidth(scene.getWidth());
        GridPane.setHgrow(btnBox, Priority.ALWAYS);
        GridPane.setVgrow(btnBox, Priority.ALWAYS);

        BackgroundFill fill = new BackgroundFill(new Color(0.243,0.329,0.275,1), null, null);
        Background bkgd = new Background(fill, null);
        root.setBackground(bkgd);

        root.getChildren().addAll(task,btnBox);
        GridPane.setRowIndex(task, 0);
        GridPane.setRowIndex(btnBox, 1);
        stage.setTitle("Random Interval Timer");
        stage.setScene(scene);
        stage.show();
    }

    private Label createFormattedTask(){
        task = new Label("Work Time");
        task.setAlignment(Pos.CENTER);
        task.setFont(Font.font("Nunito", 50));
        task.setTextFill(Color.WHITE);
        task.setTextAlignment(TextAlignment.CENTER);
        return task;
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

        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(25);

        HBox.setHgrow(playCtrl, Priority.ALWAYS);
        HBox.setHgrow(stopCtrl, Priority.ALWAYS);
        return btnBox;
    }

    public void setTaskDisplay(String text){
        task.setText(text);
    }

    public void togglePlayImage(){
        playCtrl.setImage(playCtrl.getImage().equals(play) ? pause : play);
        playCtrl.setPickOnBounds(!playCtrl.isPickOnBounds());
    }

    public static void main(String[] args) {
        launch();
    }

    public void loadAssets(){
        String resourcePath = System.getProperty("user.dir") + "\\src\\main\\resources";
        play = new Image(resourcePath + "\\play_icon.png", 100, 100, true, true);
        pause = new Image(resourcePath + "\\pause_icon.png", 100, 100, true, true);
        File nunitoDir = new File(resourcePath + "\\nunito");
        for(File file: nunitoDir.listFiles()){
            int idxOfEnding = file.getAbsolutePath().lastIndexOf(".");
            String fileEnding = file.getAbsolutePath().substring(idxOfEnding, file.getAbsolutePath().length());
            if(fileEnding.equals(".ttf")){
                Font currFont = Font.loadFont("file:" + file.getAbsolutePath(), 0);
            }
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
        controller.close();
}
}
