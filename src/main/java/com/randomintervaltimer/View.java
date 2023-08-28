package com.randomintervaltimer;

import java.io.File;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class View extends Application {
    private Controller controller = new Controller();
    ImageView playCtrl;
    Image play;
    Image pause;
    Label task;
    List<Font> fonts;
    Stage stage;

    public void start(Stage stage){
        this.stage = stage;
        stage.setMinWidth(100);
        stage.setMinHeight(175);
        controller.connectView(this);
        loadAssets();

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 400, 300);
        scene.setFill(Color.TRANSPARENT);

        GridPane toolbar = createToolbar();        
        toolbar.prefWidthProperty().bind(scene.widthProperty());
        // FOR DEBUG
        // toolbar.setGridLinesVisible(true);
        // toolbar.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

        task = createFormattedTask();
        task.prefWidthProperty().bind(scene.widthProperty());
        task.prefHeightProperty().bind(Bindings.divide(scene.heightProperty(), 10));
        GridPane.setHgrow(task, Priority.ALWAYS);
        GridPane.setVgrow(task, Priority.SOMETIMES);

        HBox btnBox = createButtons();
        btnBox.prefWidthProperty().bind(scene.widthProperty());
        GridPane.setHgrow(btnBox, Priority.ALWAYS);
        GridPane.setVgrow(btnBox, Priority.ALWAYS);

        BackgroundFill fill = new BackgroundFill(Palette.Green, new CornerRadii(15), null);
        Background bkgd = new Background(fill);
        root.setBackground(bkgd);

        BorderStroke bStroke = new BorderStroke(Palette.DarkestGreen, BorderStrokeStyle.SOLID, new CornerRadii(13), new BorderWidths(2), Insets.EMPTY);
        root.setBorder(new Border(bStroke));

        root.getChildren().addAll(toolbar, task,btnBox);
        GridPane.setRowIndex(task, 1);
        GridPane.setRowIndex(btnBox, 2);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Random Interval Timer");
        stage.setScene(scene);
        ResizeHelper.addResizeListener(stage);
        scene.widthProperty().addListener(e -> {
            btnBox.getChildren().forEach(child -> {
                if(child instanceof ImageView){
                    double newImgSize = Math.min(Math.min(stage.getHeight(), stage.getWidth()) / 3, 100);
                    ((ImageView)child).setPreserveRatio(true);
                    ((ImageView)child).setFitWidth(newImgSize);
                }
            });
        });
        stage.show();
    }

    private Label createFormattedTask(){
        task = new Label("Work Time");
        task.setAlignment(Pos.CENTER);
        task.setFont(Font.font("Nunito", 50));
        task.widthProperty().addListener(e ->{
            Text defaultText = new Text("Break Time");
            defaultText.setFont(Font.font(task.getFont().getFamily(), 50));
            double defaultWidth = defaultText.getLayoutBounds().getWidth();

            Text currText = new Text("Break Time");
            currText.setFont(task.getFont());
            double currWidth = defaultText.getLayoutBounds().getWidth();
            
            boolean toGrow = task.getWidth() > currWidth && currText.getFont().getSize() < 50;
            boolean toShrink = task.getWidth() < currWidth;
            if(toGrow || toShrink){
                int newSize = (int) Math.round(Math.min(50, 50 * task.getWidth() / defaultWidth));
                task.setFont(Font.font("Nunito", newSize));
            }
        });
    
        task.setTextFill(Color.WHITE);
        task.setTextAlignment(TextAlignment.CENTER);
        return task;
    }

    private HBox createButtons(){
        HBox btnBox = new HBox();
        String sep = File.separator;
        String dir = "src" + sep + "main" + sep + "resources";
        ImageView stopCtrl = new ImageView(play);
        Image stop = new Image("file:" + dir + sep + "stop_icon.png", 100, 100, true, true);
        stopCtrl = new ImageView(stop);
        stopCtrl.setOnMouseClicked(e -> {
            controller.handleStopButtonClick();
        });
        playCtrl = new ImageView(play);
        playCtrl.setOnMouseClicked(e -> {
            controller.handleStartButtonClick();
        });


        btnBox.getChildren().addAll(playCtrl, stopCtrl);

        btnBox.setAlignment(Pos.CENTER);
        btnBox.spacingProperty().bind(Bindings.divide(playCtrl.fitWidthProperty(), 4));

        HBox.setHgrow(playCtrl, Priority.ALWAYS);
        //HBox.setHgrow(stopCtrl, Priority.ALWAYS);
        return btnBox;
    }

    public GridPane createToolbar(){
        Button settings = new Button("Settings");
        settings.setOnMouseClicked(e -> {
            SettingsView settingsWindow = new SettingsView(controller.getSettings());
            Stage settingsStage = new Stage();
            settingsWindow.start(settingsStage);
        });
        settings.setAlignment(Pos.CENTER_LEFT);
       
        Button exit = new Button("x");
        exit.setOnMouseClicked(e -> {
            Platform.exit();
        });
        exit.setAlignment(Pos.CENTER_RIGHT);

        Button minimize = new Button("-");
        minimize.setOnMouseClicked(e -> {
            stage.setIconified(true);
        });
        minimize.setAlignment(Pos.CENTER_RIGHT);

        GridPane toolbar = new GridPane();
        
        toolbar.setMinHeight(30);
        GridPane.setVgrow(toolbar, Priority.NEVER);
        toolbar.setPadding(new Insets(10));
        toolbar.setHgap(5);

        toolbar.setOnMousePressed(pressEvent -> {
            toolbar.setOnMouseDragged(dragEvent -> {
                stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });

        CornerRadii btnRadii = new CornerRadii(5);
        for (Button btn : new Button[]{settings, minimize, exit}) {
            btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, btnRadii, null)));
            btn.setTextFill(Color.WHITE);
            btn.setFont(Font.font("Nunito ExtraBold", btn.getFont().getSize()));
        }

        CornerRadii innerRadii = new CornerRadii(15, 15, 0, 0, false);
        CornerRadii outerRadii = new CornerRadii(13,13,0,0,false);
        toolbar.setBackground(new Background(new BackgroundFill(Palette.DarkGreen, innerRadii, null)));

        BorderStroke bStroke = new BorderStroke(Palette.DarkestGreen, Palette.DarkestGreen, Palette.DarkestGreen, Palette.DarkestGreen, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, outerRadii, new BorderWidths(2), Insets.EMPTY);
        toolbar.setBorder(new Border(bStroke));

        toolbar.getChildren().addAll(settings,minimize,exit);

        GridPane.setColumnIndex(settings, 0);
        GridPane.setHalignment(settings, HPos.LEFT);
        GridPane.setHgrow(settings, Priority.SOMETIMES);

        GridPane.setColumnIndex(minimize, 1);
        GridPane.setHalignment(minimize, HPos.RIGHT);

        GridPane.setColumnIndex(exit,2);
        GridPane.setHalignment(exit, HPos.RIGHT);
         
        return toolbar;
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
        String sep = File.separator;
        String resourcePath = "src" + sep + "main" + sep + "resources";
        play = new Image("file:" + resourcePath + sep + "play_icon.png", 100, 100, true, true);
        pause = new Image("file:" + resourcePath + sep + "pause_icon.png", 100, 100, true, true);

        File nunitoDir = new File(resourcePath + sep + "nunito");
        for(File file: nunitoDir.listFiles()){
            int idxOfEnding = file.getAbsolutePath().lastIndexOf(".");
            String fileEnding = file.getAbsolutePath().substring(idxOfEnding, file.getAbsolutePath().length());
            if(fileEnding.equals(".ttf")){
                Font.loadFont("file:" + file.getAbsolutePath(), 0);
            }
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        controller.close();
}
}
