package com.maxxbyte.robo_dash.controllers;


import com.maxxbyte.robo_dash.data.ProfileDao;
import com.maxxbyte.robo_dash.data.UserDao;
import com.maxxbyte.robo_dash.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

    private final ProfileDao profileDao;
    private final UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userDao.getByUserName(username);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable int userId, @RequestBody String firstName, String lastName, String phone, String email, String address, String city, String state, String zip)
    {
        User user = getCurrentUser();
    }
}
