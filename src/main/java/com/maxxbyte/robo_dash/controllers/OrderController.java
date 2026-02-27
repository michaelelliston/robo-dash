package com.maxxbyte.robo_dash.controllers;

import com.maxxbyte.robo_dash.data.LocationDao;
import com.maxxbyte.robo_dash.models.Robot;
import com.maxxbyte.robo_dash.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {
    private Robot robot;
    private LocationDao locationDao;

    @Autowired
    public OrderController(Robot robot, LocationDao locationDao) {
        this.robot = robot;
        this.locationDao = locationDao;
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Route assignRoute(@PathVariable int id) {
        return robot.assignRoute(id);
    }


}
