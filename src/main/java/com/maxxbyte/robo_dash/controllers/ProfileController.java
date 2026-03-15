package com.maxxbyte.robo_dash.controllers;


import com.maxxbyte.robo_dash.data.ProfileDao;
import com.maxxbyte.robo_dash.data.UserDao;
import com.maxxbyte.robo_dash.models.Profile;
import com.maxxbyte.robo_dash.models.User;
import com.maxxbyte.robo_dash.models.dto.ProfileDto;
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

    @GetMapping
    public ProfileDto getProfile()
    {
        User user = getCurrentUser();
        Profile profile = profileDao.getById(user.getId());

        if (profile == null) {
            return new ProfileDto();
        }
        ProfileDto profileDto = new ProfileDto();
        profileDto.setFirstName(profile.getFirstName());
        profileDto.setLastName(profile.getLastName());
        profileDto.setCity(profile.getCity());
        profileDto.setState(profile.getState());
        profileDto.setZip(profile.getZip());
        profileDto.setPhone(profile.getPhone());
        profileDto.setAddress(profile.getAddress());
        profileDto.setEmail(profile.getEmail());
        return  profileDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProfile(@RequestBody ProfileDto profileDto)
    {
        User user = getCurrentUser();
        Profile profile = new Profile();

        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setEmail(profileDto.getEmail());
        profile.setPhone(profileDto.getPhone());
        profile.setAddress(profileDto.getAddress());
        profile.setCity(profileDto.getCity());
        profile.setState(profileDto.getState());
        profile.setZip(profileDto.getZip());

        profileDao.update(user.getId(), profile);
    }
}
