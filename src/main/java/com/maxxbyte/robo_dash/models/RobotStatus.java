package com.maxxbyte.robo_dash.models;

public enum RobotStatus {
    IDLE(true),
    DELIVERING(false),
    CHARGING(false),
    STUCK(false),
    CRASHED(false);

    private final boolean available;

    RobotStatus(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }
}
