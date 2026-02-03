package com.maxxbyte.robo_dash.models;

public class Robot {

    //TODO: implement battery drain based on distance measurement
    private int id;
    private int batteryLevel = 100; // Percentage of battery
    private final int walkwaySpeed = 15;
    private final int roadwaySpeed = 30; //TODO: Consider using an enum for SurfaceType, and assign different speeds to those.
    private RobotStatus status = RobotStatus.IDLE;
    private boolean turnSignal = false;
    private Order currentOrder;
    // Location currentLocation;  TODO: Create a Location model
    // Route currentRoute;   TODO: Create a Route model

    public Robot() {

    }

    //TODO: implement ability to obtain customer location via their ID, map a route, and then start the delivery.
    public void assignOrder(Order order, int customerId) {
        this.currentOrder = order;

        if (this.batteryLevel <= 40) {
            chargeBattery();
        }
    }

    public void chargeBattery() {
        if (batteryLevel < 100) {
            status = RobotStatus.CHARGING;
            while (batteryLevel < 100) {
                batteryLevel++;
            }
        }
        status = RobotStatus.IDLE;
    }

    public void toggleTurnSignal() {
        turnSignal = !turnSignal;
    }

    public int getId() {
        return this.id;
    }

    public RobotStatus getStatus() {
        return status;
    }

    // Parameters are assumed, use as necessary. TODO:
    // public Location getCustomerLocation(int customerId) {}
    // public Location getCurrentLocation() {}
    // public Route mapRoute(Location customerLocation, Location currentLocation) {}
    // public Route getCurrentRoute() {}
    //
}
