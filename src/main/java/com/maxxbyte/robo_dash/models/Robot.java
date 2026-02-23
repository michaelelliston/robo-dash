package com.maxxbyte.robo_dash.models;

import com.maxxbyte.robo_dash.services.NavigationService;

public class Robot {

    //TODO: implement battery drain based on meters traveled
    private int id;
    private int batteryLevel; // Percentage of battery
    private RobotStatus status;
    private boolean turnSignal;
    private NavigationService navigationService;
    private Order currentOrder;
    private Location currentLocation;
    private Location homeLocation;
    private Route currentRoute;

    public Robot() {}

    public Robot(int id, NavigationService navigationService) {
        this.id = id;
        this.batteryLevel = 100;
        this.status = RobotStatus.IDLE;
        this.turnSignal = false;
        this.homeLocation = navigationService.getLocationById(61);
    }

    //TODO: implement ability to obtain customer location via their ID, map a route, and then start the delivery.

    //public void startDelivery(Route deliveryRoute) {
        //status = RobotStatus.DELIVERING;
        //currentRoute = mapRoute(getCustomerLocation(customerId), currentLocation);
    //}

    public void assignRoute() {
        Location destination = navigationService.getLocationById(currentOrder.getDeliveryLocationId());
        currentRoute = navigationService.calculateRoute(homeLocation, destination);
        System.out.println(currentRoute);
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

    // Parameters are assumed, use as necessary.

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Location getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Route getCurrentRoute() {
        return this.currentRoute;
    }
}
