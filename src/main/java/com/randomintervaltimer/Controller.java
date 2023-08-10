package com.randomintervaltimer;

public class Controller {
    private Model model;


    Controller(){
        model = new Model();
    }

    public void handleStartButtonClick(){
        State status = model.getStatus();
        if(status.isPaused()){
            model.resume();
        } else if (status.getTask() == State.Task.Work || status.getTask() == State.Task.Break){
            model.pause();
        } else if (status.getTask() == State.Task.BeforeWork){
            model.workTime();
        } else{
            model.breakTime();
        }
    }

    public void handleStopButtonClick(){
        System.out.println("Stop button has been pressed");
        State status = model.getStatus();
        if(status.getTask() == State.Task.Work || status.getTask() == State.Task.Break){
            model.stop();
        }
    }
}
