package com.maxxbyte.robo_dash.models;

import com.maxxbyte.robo_dash.services.NavigationService;

public class Robot {

    //TODO: implement battery drain based on meters traveled
    private int id;
    private int batteryLevel; // Percentage of battery
    private SurfaceType currentSpeed;
    private RobotStatus status;
    private boolean turnSignal;
    private NavigationService navigationService;
    private Order currentOrder;
    private Location currentLocation;
    //private final Location homeLocation;
    private Route currentRoute;

    public Robot() {}

    public Robot(int id) {
        this.id = id;
        this.batteryLevel = 100;
        this.status = RobotStatus.IDLE;
        this.turnSignal = false;
        //this.homeLocation = LocationDao.getLocationById(61);
    }

    //TODO: implement ability to obtain customer location via their ID, map a route, and then start the delivery.
    public void assignOrder(Order order, int customerId) {
        this.currentOrder = order;

        if (this.batteryLevel <= 40) {
            chargeBattery();
        }
        calculateDeliveryRoute(currentOrder);
    }

    //public void startDelivery(int customerId) {
        //status = RobotStatus.DELIVERING;
        //currentRoute = mapRoute(getCustomerLocation(customerId), currentLocation);
    //}

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

    public Order getCurrentOrder() {
        return this.currentOrder;
    }

    public int getId() {
        return this.id;
    }

    public RobotStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SurfaceType getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(SurfaceType currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    // Parameters are assumed, use as necessary.
    // public Location getCustomerLocation(int customerId) {}

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    // TODO: Implement this method
    public void calculateDeliveryRoute(Order order) {

    }


    public Route getCurrentRoute() {
        return this.currentRoute;
    }
}
