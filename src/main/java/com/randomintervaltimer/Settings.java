package com.randomintervaltimer;

public class Settings {

    private int workMin;
    private int workMax;
    private int breakMin;
    private int breakMax;

    Settings(){
    workMin = 30;
    workMax = 70;
    breakMin = 10;
    breakMax = 20;
    }

    public int getBreakMin() {
        return breakMin;
    }

    public void setBreakMin(int breakMin) {
        this.breakMin = breakMin;
    }

    public int getBreakMax() {
        return breakMax;
    }

    public void setBreakMax(int breakMax) {
        this.breakMax = breakMax;
    }

    public int getWorkMin() {
        return workMin;
    }

    public void setWorkMin(int workMin) {
        this.workMin = workMin;
    }

    public int getWorkMax() {
        return workMax;
    }

    public void setWorkMax(int workMax) {
        this.workMax = workMax;
    }

}