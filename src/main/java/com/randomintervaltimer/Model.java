package com.randomintervaltimer;

public class Model{

    public State status;
    public Settings settings;
    private Timer timer;
    private AlarmListener listener;

    Model(){
        settings = new Settings();
        status = new State();
        timer = new Timer(settings.getWorkMin());
        listener = new AlarmListener();
    }

    public State getStatus() {
        return status;
    }

    public void breakTime(){
        status.nextTask();
        System.out.println(status.getTask());

        timer.setDuration(calculateBreakInterval());
        timer.start();

        listener = new AlarmListener();
        listener.start();
    }
    
    public void workTime(){
        status.nextTask();
        System.out.println(status.getTask());

        timer.setDuration(calculateWorkInterval());
        timer.start();

        listener = new AlarmListener();
        listener.start();
    }

    public void pause(){
        System.out.println("Pausing in Model");
        status.setPaused(true);
        timer.pause();
    }

    public void resume(){
        System.out.println("Resuming in Model");
        status.setPaused(false);
        timer.resume();
    }

    public void stop(){
        listener.interrupt();
        System.out.println("Stopping in Model");
        status.nextTask();
        System.out.println(status.getTask());
        timer.reset();
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

    private class AlarmListener extends Thread{
        @Override
        public void run(){
            while(timer.isRunning());
            if(!interrupted()){
                System.out.println("Stopping in Listener");
                status.nextTask();
                System.out.println(status.getTask());
                timer.reset();
            }
        }
    }
}