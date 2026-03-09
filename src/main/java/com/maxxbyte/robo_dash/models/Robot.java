package com.maxxbyte.robo_dash.models;

import com.maxxbyte.robo_dash.services.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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

    @Autowired
    public Robot(NavigationService navigationService) {
        this.batteryLevel = 100;
        this.status = RobotStatus.IDLE;
        this.turnSignal = false;
        this.navigationService = navigationService;
        this.homeLocation = navigationService.getLocationById(61);
    }

    public void assignRoute(int locationId) {
        Location destination = navigationService.getLocationById(locationId);
        currentRoute = navigationService.calculateRoute(homeLocation, destination);
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

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
        assignRoute(currentOrder.deliveryLocationId);
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
