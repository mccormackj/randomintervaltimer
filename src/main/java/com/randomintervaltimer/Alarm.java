package com.randomintervaltimer;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Alarm {
    String notifPath;
    Media sound;
    MediaPlayer mediaPlayer;
    Alarm(){
        URL notifURL = this.getClass().getResource("/mixkit-bell-notification-933.wav");
        sound = new Media(notifURL.toExternalForm());

        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> {this.mediaPlayer = new MediaPlayer(sound);});
    }

    public void play(){
        mediaPlayer.play();
    }

}
