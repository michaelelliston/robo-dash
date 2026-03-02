package com.maxxbyte.robo_dash.services;

import com.maxxbyte.robo_dash.data.LocationDao;
import com.maxxbyte.robo_dash.data.PathDao;
import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.Path;
import com.maxxbyte.robo_dash.models.Route;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NavigationService {

    private List<Location> locationList;
    private List<Path> pathList;
    private Map<Integer, List<Path>> pathMap = new HashMap<>();
    private PathDao pathDao;
    private LocationDao locationDao;

    public NavigationService(PathDao pathDao, LocationDao locationDao) {
        this.pathDao = pathDao;
        this.locationDao = locationDao;
    }

    // Populates an Adjacency List from Database
    @PostConstruct
    public void initializeMap() {

        locationList = locationDao.getAllLocations();
        pathList = pathDao.getAllPaths();

        for (Location location : locationList) {
            pathMap.put(location.getLocationId(), new ArrayList<>());
        }

        for (Path path : pathList) {
            int fromLocationId = path.getFromLocationId();
            if (pathMap.containsKey(fromLocationId)) {
                pathMap.get(fromLocationId).add(path);
            }
        }
        System.out.println("Map initialized");
        System.out.println("Neighbors of 61: " + pathMap.get(61));
    }

    public Route calculateRoute(Location startLocation, Location destination) {

        int startId = startLocation.getLocationId();
        int destId = destination.getLocationId();

        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Path> previousPath = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<Integer> queue =
                new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (Integer locationId : pathMap.keySet()) {
            distances.put(locationId, Double.POSITIVE_INFINITY);
        }

        distances.put(startId, 0.0);
        queue.add(startId);

        while (!queue.isEmpty()) {
            int currentId = queue.poll();

            if (visited.contains(currentId)) continue;
            visited.add(currentId);

            if (currentId == destId) break;

            for (Path path : pathMap.getOrDefault(currentId, new ArrayList<>())) {
                int neighborId = path.getToLocationId();

                if (visited.contains(neighborId)) continue;

                double newDistance = distances.get(currentId) + path.getDistance();

                if (newDistance < distances.getOrDefault(neighborId, Double.POSITIVE_INFINITY)) {
                    distances.put(neighborId, newDistance);
                    previousPath.put(neighborId, path);
                    queue.add(neighborId);
                }
            }
        }

        return reconstructRoute(startLocation, destination, previousPath);
    }

    private Route reconstructRoute(Location startLocation, Location destination, Map<Integer, Path> previousPath) {
        List<Path> routePaths = new ArrayList<>();
        Location currentLocation = destination;
        while (!currentLocation.equals(startLocation)) {

            Path path = previousPath.get(currentLocation.getLocationId());
            if (path == null) {
                return new Route(startLocation, destination, new ArrayList<>());
            }

            routePaths.add(path);
            currentLocation = locationDao.getLocationById(path.getFromLocationId());
        }

        Collections.reverse(routePaths);
        return new Route(startLocation, destination, new ArrayList<>(routePaths));
    }

    public Location getLocationById(int id) {
        return locationDao.getLocationById(id);
    }
}
