package com.randomintervaltimer;

import  java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.animation.AnimationTimer;


public class Timer extends AnimationTimer{
    private Duration duration;
    private Duration updatingDuration = Duration.ZERO;
    private long startTime = Long.MIN_VALUE;
    private long pauseTime = Long.MIN_VALUE;
    private boolean toPause = false;
    private Alarm alarm = new Alarm();
    private AtomicBoolean running = new AtomicBoolean(false);

    Timer(long duration, ChronoUnit unit){
        setDuration(duration, unit);
    }

    Timer(long duration){
        setDuration(duration);
    }

    public Alarm getAlarm(){
        return alarm;
    }

    public void setDuration(long duration, ChronoUnit unit) {
        this.duration = Duration.of(duration, unit);
    }

    public void setDuration(long duration) {
        this.duration = Duration.ofSeconds(duration);
    }

    // public boolean getAlarm(){
    //     return alarm;
    // }

    // public boolean resetAlarm() {
    //     if(alarm){
    //         alarm = false;
    //         return true;
    //     }
    //     return false;
    // }

    @Override
    public void start(){
        updatingDuration = Duration.ZERO;
        toPause = false;
        running.set(true);
        //alarm = false;
        super.start();
    }

    public void resume(){
        toPause = false;
        super.start();
    }

    public void pause(){
        toPause = true;
    }

    @Override
    public void handle(long now){
        if(toPause){
            pauseTime = now;
            toPause = false;
            stop();
        } else if (startTime == Long.MIN_VALUE){
            // starting timer
            startTime = now;
            pauseTime = Long.MIN_VALUE;
        } else if (pauseTime != Long.MIN_VALUE) {
            // timer unpaused
            startTime += (now - pauseTime);
            pauseTime = Long.MIN_VALUE;
        } else {
            updatingDuration = Duration.ofNanos(now - startTime);
            if(updatingDuration.compareTo(duration) >= 0){
                //alarm here?
                alarm.play();
                reset();
            }
        }
    }

    public void reset(){
        stop();
        running.set(false);;
        startTime = Long.MIN_VALUE;
        pauseTime = Long.MIN_VALUE;
        toPause = false;
        updatingDuration = Duration.ZERO;
    }

    public boolean isRunning(){
        return running.get();
    }
}
