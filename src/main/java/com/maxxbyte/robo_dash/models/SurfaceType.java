package com.maxxbyte.robo_dash.models;

public enum SurfaceType {
    WALKWAY(15),
    ROADWAY(30);

    private final int milesPerHour;

    SurfaceType(int milesPerHour) {
        this.milesPerHour = milesPerHour;
    }

    public int getMilesPerHour(){
        return milesPerHour;
    }
}
