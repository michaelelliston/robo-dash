package com.maxxbyte.robo_dash.models;

public class Robot {

    //TODO: implement battery drain based on distance measurement
    int id;
    int batteryLevel = 100; // Percentage of battery
    int walkwaySpeed = 15;
    int roadwaySpeed = 30; //TODO: Consider using an enum for SurfaceType, and assign different speeds to those.
    RobotStatus status = RobotStatus.IDLE;
    boolean turnSignal = false;
    Order currentOrder;
    // Location currentLocation;  TODO: Create a Location model
    // Route currentRoute;   TODO: Create a Route model

    public Robot() {

    }

    //TODO: implement ability to obtain customer location via their ID, map a route, and then start the delivery.
    public void startDelivery(Order order, int customerId) {
        this.currentOrder = order;

    }



    public int getId() {
        return this.id;
    }

    public RobotStatus getStatus() {
        return status;
    }

    //public Location getLocation() {}

    //public Route getRoute() {}
}
