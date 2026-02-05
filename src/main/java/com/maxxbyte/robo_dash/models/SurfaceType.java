package com.maxxbyte.robo_dash.models;

public enum SurfaceType {
    WALKWAY(6.7),
    ROADWAY(13.4);

    private final double metersPerSecond;

    SurfaceType(double metersPerSecond) {
        this.metersPerSecond = metersPerSecond;
    }

    public double getMetersPerSecond(){
        return metersPerSecond;
    }
}
