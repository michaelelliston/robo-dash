package com.maxxbyte.robo_dash.models;

public enum OrderStatus {
    COMPLETED(true),
    IN_PROGRESS(false),
    CANCELLED(false);

    private final boolean isDelivered;

    OrderStatus(boolean isCompleted) {
        this.isDelivered = isCompleted;
    }

    public boolean isDelivered() {
        return isDelivered;
    }
}
