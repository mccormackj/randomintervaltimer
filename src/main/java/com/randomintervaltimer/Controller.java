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

    public void playPauseToggle(){
        view.togglePlayImage();
    }

    public void taskChange(){
        State.Task currTask = model.getStatus().getTask();
        String text = (currTask == State.Task.Work || currTask == State.Task.BeforeWork) ? "Work Time": "Break Time";
        view.setTaskDisplay(text);
    }


    public void connectView(View view){
        this.view = view;
    }

    public void connectModel(Model model){
        this.model = model;
    }

    public Settings getSettings(){
        return model.getSettings();
    }

    public void close(){
        model.stop();
    }
}
