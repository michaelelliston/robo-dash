package com.maxxbyte.robo_dash.services;

import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
