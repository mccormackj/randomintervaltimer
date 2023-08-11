package com.randomintervaltimer;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Alarm {
    Path notifPath;
    Media sound;
    MediaPlayer mediaPlayer;
    Alarm(){
        Path dir = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources");
        notifPath = dir.resolve("mixkit-bell-notification-933.wav"); 
        
        sound = new Media(notifPath.toUri().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> {this.mediaPlayer = new MediaPlayer(sound);});
    }
    
    public void play(){
        mediaPlayer.play();
    }

}
