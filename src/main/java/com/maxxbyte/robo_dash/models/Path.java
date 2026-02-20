package com.maxxbyte.robo_dash.models;

public class Path {

    int pathId;
    int fromLocationId;
    int toLocationId;
    int distance; // measured in meters
    PathType pathType;
    Location toLocation;
    Location fromLocation;

    public Path() {}

    public Path(int pathId, int fromLocationId, int toLocationId, int distance, PathType pathType) {
        this.pathId = pathId;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.distance = distance;
        this.pathType = pathType;
    }

    public Path(int pathId, int fromLocationId, int toLocationId, int distance, PathType pathType, Location toLocation, Location fromLocation) {
        this.pathId = pathId;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.distance = distance;
        this.pathType = pathType;
        this.toLocation = toLocation;
        this.fromLocation = fromLocation;
    }

    public int getId() {
        return pathId;
    }

    public void setId(int pathId) {
        this.pathId = pathId;
    }

    public int getFromLocationId() {
        return fromLocationId;
    }

    public void setFromLocationId(int fromLocationId) {
        this.fromLocationId = fromLocationId;
    }

    public int getToLocationId() {
        return toLocationId;
    }

    public void setToLocationId(int toLocationId) {
        this.toLocationId = toLocationId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public PathType getPathType() {
        return pathType;
    }

    public void setPathType(PathType pathType) {
        this.pathType = pathType;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
    }


}
