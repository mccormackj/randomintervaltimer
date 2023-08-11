package com.randomintervaltimer;

public class Controller {
    private Model model;
    private View view;

    Controller(){
        model = new Model(this);
    }

    public void handleStartButtonClick(){
        State status = model.getStatus();
        if(status.isPaused()){
            model.resume();
            view.togglePlayImage();
        } else if (status.getTask() == State.Task.Work || status.getTask() == State.Task.Break){
            model.pause();
            view.togglePlayImage();
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

    public void taskFinished(){
        view.togglePlayImage();
    }


    public void connectView(View view){
        this.view = view;
    }

    public void connectModel(Model model){
        this.model = model;
    }
}
