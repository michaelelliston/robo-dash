package com.maxxbyte.robo_dash.models;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private Location startingLocation;
    private Location destination;
    private List<Path> paths;
    private int totalDistance;

    public Route() {}

    public Route(Location startingLocation, Location destination, List<Path> paths, int totalDistance) {
        this.startingLocation = startingLocation;
        this.destination = destination;
        this.paths = paths;
        this.totalDistance = totalDistance;
    }

    public Route(Location startLocation, Location destination, ArrayList<Path> paths) {
        this.startingLocation = startLocation;
        this.destination = destination;
        this.paths = paths;
    }

    public Location getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(Location startingLocation) {
        this.startingLocation = startingLocation;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }
}
