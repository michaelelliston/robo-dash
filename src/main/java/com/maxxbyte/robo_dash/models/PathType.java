package com.maxxbyte.robo_dash.models;

public enum PathType {
    // Speed measured in meters per second
    SIDEWALK(6.7),
    ROAD(13.4);

    private final double pathSpeed;

    PathType(double pathSpeed) {
        this.pathSpeed = pathSpeed;
    }

    public double getPathSpeed() {
        return pathSpeed;
    }
}
