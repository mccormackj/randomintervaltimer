package com.randomintervaltimer;

public class State {
    
    private boolean paused;
    private Task task;

    State(){
        paused = false;
        task = Task.BeforeWork;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void nextTask(){
        task = task.next();
    }

    public enum Task{
        BeforeWork,
        Work,
        BeforeBreak,
        Break;

        private static final Task[] vals = values();
        
        public Task next() {
            return vals[(this.ordinal() + 1) % vals.length];
        }
    }

}

