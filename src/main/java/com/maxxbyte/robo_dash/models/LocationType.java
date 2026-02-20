package com.maxxbyte.robo_dash.models;

public enum LocationType {
    INTERSECTION(false),
    DROPOFF(true);

    private final boolean isDestination;

    LocationType(boolean isDestination) {
        this.isDestination = isDestination;
    }

    public boolean isDestination() {
        return isDestination;
    }
}
