package com.maxxbyte.robo_dash.controllers;

import com.maxxbyte.robo_dash.data.PathDao;
import com.maxxbyte.robo_dash.models.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("paths")
@CrossOrigin
public class PathsController {
    private PathDao pathDao;

    @Autowired
    public PathsController(PathDao pathDao) {
        this.pathDao = pathDao;
    }

    @GetMapping("")
    @PreAuthorize("permitAll()")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Path> getPaths() {
        try {
            List<Path> paths = pathDao.getAllPaths();

            if (paths.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return paths;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server error");
        }
    }
}
