package com.randomintervaltimer;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Timer{

    public State status;
    public Settings settings;

    Timer(){
        settings = new Settings();
        status = new State();
    }

    public State getStatus() {
        return status;
    }

    public void breakTime(){
        timeHelper(calculateBreakInterval());
        
    }
    
    public void workTime(){
        timeHelper(calculateWorkInterval());
    }

    private void timeHelper(int duration){
        status.nextTask();
        LocalTime start = LocalTime.now();
        while(ChronoUnit.MINUTES.between(start, LocalTime.now()) < duration);
        alarm();
        status.nextTask();
    }

    private void alarm(){
        System.out.println(status.getTask() + " Time is over!");
        //TODO: Handle alarm
    }

    public void pause(){
        //TODO: Handle Pause
    }

    public void resume(){
        //TODO: Handle resume
    }

    public int calculateWorkInterval(){
        return randomBetween(settings.getWorkMin(), settings.getWorkMax());
    }

    public int calculateBreakInterval(){
        return randomBetween(settings.getBreakMin(), settings.getBreakMax());
    }

    private int randomBetween(int min, int max){
        return (int)(Math.round(Math.random() * (max - min) + min));
    }

}