package com.maxxbyte.robo_dash.models;

public class Path {

    int id;
    int fromLocationId;
    int toLocationId;
    int distance; // measured in meters
    PathType pathType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
