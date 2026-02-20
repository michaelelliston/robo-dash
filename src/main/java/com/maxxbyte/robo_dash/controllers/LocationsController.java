package com.maxxbyte.robo_dash.controllers;


import com.maxxbyte.robo_dash.data.LocationDao;
import com.maxxbyte.robo_dash.models.Location;
import com.maxxbyte.robo_dash.models.LocationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("locations")
@CrossOrigin
public class LocationsController {
    private LocationDao locationDao;

    @Autowired
    public LocationsController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Location> getAllLocations() {
        try {
            List<Location> locations = locationDao.getAllLocations();

            if (locations.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return locations;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.OK)
    public Location getLocationById(@PathVariable int id) {
        try {
            Location location = locationDao.getLocationById(id);
            if (location == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return location;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }
    }

    @GetMapping("type/{locationType}")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Location> getLocationsByLocationType(@PathVariable String locationType) {
        try {

            List<Location> locations = locationDao.getLocationsByType(locationType);
            if (locations.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return locations;

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }
    }

}