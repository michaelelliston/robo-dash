package com.maxxbyte.robo_dash.services;

import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.Path;
import com.maxxbyte.robo_dash.models.Route;

import java.util.*;

public class NavigationService {

    private final List<Location> locationList;
    private final List<Path> pathList;
    private Map<Location, List<Path>> pathMap;

    public NavigationService(List<Location> locationList, List<Path> pathList) {
        this.locationList = locationList;
        this.pathList = pathList;
    }

    // Populates an Adjacency List from Database
    public void initializeMap() {

        for (Location location : locationList) {
            pathMap.put(location, new ArrayList<>());
        }

        for (Path path : pathList) {
            Location fromLocation = path.getFromLocation();
            if (pathMap.containsKey(fromLocation)) {
                pathMap.get(fromLocation).add(path);
            }
        }
    }

    public Route calculateRoute(Location startLocation, Location destination) {
        Map<Location, Double> distances = new HashMap<>();
        Map<Location, Path> previousPath = new HashMap<>();
        Set<Location> visited = new HashSet<>();

        PriorityQueue<Location> queue = new PriorityQueue<>(
                Comparator.comparingDouble(distances::get)
        );

        for (Location location : pathMap.keySet()) {
            distances.put(location, Double.POSITIVE_INFINITY);
        }

        distances.put(startLocation, 0.0);
        queue.add(startLocation);

        while (!queue.isEmpty()) {

            Location currentLocation = queue.poll();
            if (visited.contains(currentLocation)) {
                continue;
            }
            visited.add(currentLocation);
            if (currentLocation.equals(destination)) {
                break;
            }

            for (Path path : pathMap.get(currentLocation)) {
                Location neighborLocation = path.getToLocation();
                if (visited.contains(neighborLocation)) {
                    continue;
                }
                double newDistance = distances.get(currentLocation) + path.getDistance();

                if (newDistance < distances.get(neighborLocation)) {
                    distances.put(neighborLocation, newDistance);
                    previousPath.put(neighborLocation, path);
                    queue.add(neighborLocation);
                }
            }
        }
        return reconstructRoute(startLocation, destination, previousPath);
    }

    private Route reconstructRoute(Location startLocation, Location destination, Map<Location, Path> previousPath) {
        List<Path> routePaths = new ArrayList<>();
        Location currentLocation = destination;
        while (currentLocation != startLocation) {

            Path path = previousPath.get(currentLocation);
            if (path == null) {
                return new Route(startLocation, destination, new ArrayList<>());
            }

            routePaths.add(path);
            currentLocation = path.getToLocation();
        }

        Collections.reverse(routePaths);
        return new Route(startLocation, destination, new ArrayList<>(routePaths));
    }
}
