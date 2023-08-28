package com.randomintervaltimer;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.controlsfx.control.RangeSlider;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class SettingsView extends Application{
    Settings settings;
    
    SettingsView(Settings settings){
        this.settings = settings;
    }

    public void start(Stage stage){
        stage.initStyle(StageStyle.TRANSPARENT);
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 300, 200);
        scene.setFill(Color.TRANSPARENT);

        Label workLabel = new Label("Work Range: ");
        workLabel.setFont(Font.font("nunito", workLabel.getFont().getSize()));
        workLabel.setTextFill(Palette.DarkestGreen);
        root.add(workLabel, 0, 1);

        Label breakLabel = new Label("Break Range: ");
        breakLabel.setFont(Font.font("nunito", breakLabel.getFont().getSize()));
        breakLabel.setTextFill(Palette.DarkestGreen);
        root.add(breakLabel, 0, 2);


        RangeSlider workSlider = new RangeSlider(0, 120, settings.getWorkMin(), settings.getWorkMax());
        RangeSlider breakSlider = new RangeSlider(0, 40, settings.getBreakMin(), settings.getBreakMax());
        formatSlider(workSlider);
        formatSlider(breakSlider);
        root.add(workSlider, 1, 1);
        root.add(breakSlider, 1, 2);

        Button save = new Button("Save");
        save.setOnMouseClicked(e -> {
            settings.setBreakMin((int)Math.round(breakSlider.getLowValue()));
            settings.setBreakMax((int)Math.round(breakSlider.getHighValue()));
            settings.setWorkMin((int)Math.round(workSlider.getLowValue()));
            settings.setWorkMax((int)Math.round(workSlider.getHighValue()));
        });
        save.setAlignment(Pos.CENTER_LEFT);
       
        Button exit = new Button("x");
        exit.setOnMouseClicked(event -> {
            stage.close();
        });
        exit.setAlignment(Pos.CENTER_RIGHT);

        Button minimize = new Button("-");
        minimize.setOnMouseClicked(e -> {
            stage.setIconified(true);
        });
        minimize.setAlignment(Pos.CENTER_RIGHT);

        HBox filler = new HBox();
        GridPane.setVgrow(filler, Priority.ALWAYS);
        root.add(filler, 0, 3);
        GridPane.setColumnSpan(filler, 2);

        GridPane toolbar = new GridPane();
        
        toolbar.setMinHeight(30);
        GridPane.setVgrow(toolbar, Priority.NEVER);
        toolbar.setPadding(new Insets(5));
        toolbar.setHgap(5);

        toolbar.getChildren().addAll(save,minimize,exit);
        GridPane.setColumnIndex(save, 0);
        GridPane.setColumnIndex(minimize, 1);
        GridPane.setColumnIndex(exit,2);
        GridPane.setHalignment(save, HPos.LEFT);
        GridPane.setHalignment(minimize, HPos.RIGHT);
        GridPane.setHalignment(exit, HPos.RIGHT);
        GridPane.setHgrow(save, Priority.SOMETIMES);

        GridPane.setColumnSpan(toolbar, 2);
        GridPane.setValignment(toolbar, VPos.BOTTOM);
        toolbar.prefWidthProperty().bind(scene.widthProperty());
        root.add(toolbar, 0, 4);

        root.setPadding(new Insets(10));

        Background bkgd = new Background(new BackgroundFill((Color.WHITE), new CornerRadii(15), null));
        root.setBackground(bkgd);

        BorderStroke bStroke = new BorderStroke(Color.GAINSBORO, BorderStrokeStyle.SOLID, new CornerRadii(13), new BorderWidths(2), Insets.EMPTY);
        root.setBorder(new Border(bStroke));

        for (Button btn : new Button[]{save, minimize, exit}) {
            CornerRadii btnRadii = new CornerRadii(5);
            btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, btnRadii, null)));
            btn.setTextFill(Palette.DarkestGreen);
            btn.setFont(Font.font("Nunito ExtraBold", btn.getFont().getSize()));
        }

        //toolbar.setGridLinesVisible(true);
        //toolbar.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));

        stage.setScene(scene);
        stage.show();
    }

    public void formatSlider(RangeSlider slider){
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setPadding(new Insets(10,0,0,0));
    }

}
