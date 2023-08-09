package com.randomintervaltimer;

public class Controller {
    private Timer timer;


    Controller(){
        timer = new Timer();
    }

    public void handleButtonClick(){
        State status = timer.getStatus();
        if(status.isPaused()){
            timer.resume();
        } else if (status.getTask() == State.Task.Work || status.getTask() == State.Task.Break){
            timer.pause();
        } else if (status.getTask() == State.Task.BeforeWork){
            timer.workTime();
        } else{
            timer.breakTime();
        }
    }
}
