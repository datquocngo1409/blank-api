package com.example.demo.controller;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.RequestUser;
import com.example.demo.model.User;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class JwtAuthenController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = userService.findByUsername(authenticationRequest.getUsername());
        user.setToken(token);
        userService.updateUser(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody RequestUser user) throws Exception {
        List<User> users = userService.findAll();
        boolean isExit = false;
        for (User userFor : users) {
            if (userFor.getUsername().equals(user.getUsername())) isExit = true;
        }
        if (!isExit) {
            User userSave = new User(user.getUsername(), user.getPassword(), false);
            userService.createUser(userSave);
            return ResponseEntity.ok(userDetailsService.save(user));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(value = "/createAms", method = RequestMethod.POST)
    public ResponseEntity<?> saveAms(@RequestBody RequestUser user) throws Exception {
        List<User> users = userService.findAll();
        boolean isExit = false;
        for (User userFor : users) {
            if (userFor.getUsername().equals(user.getUsername())) isExit = true;
        }
        if (!isExit) {
            User userSave = new User(user.getUsername(), user.getPassword(), true);
            userService.createUser(userSave);
            return ResponseEntity.ok(userDetailsService.save(user));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
