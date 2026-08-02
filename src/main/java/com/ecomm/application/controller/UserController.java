package com.ecomm.application.controller;

import com.ecomm.application.UserRole;
import com.ecomm.application.beans.UserRequestBean;
import com.ecomm.application.beans.UserResponseBean;
import com.ecomm.application.entity.User;
import com.ecomm.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    //@GetMapping("/users")
    @RequestMapping(value = "users" ,method = RequestMethod.GET)
    public ResponseEntity<List<UserResponseBean>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequestBean user) {
        userService.createUser(user);
        return new ResponseEntity<>("User added Successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserResponseBean getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id , @RequestBody User user){
        boolean updated= userService.updateUser(id,user);
        if(updated) return new ResponseEntity<>("User Updated ",HttpStatus.OK);
        return new ResponseEntity<>("User NOT Found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User deleted with id: " + id;
    }

}
