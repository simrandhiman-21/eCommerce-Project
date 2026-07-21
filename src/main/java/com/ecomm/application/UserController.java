package com.ecomm.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private  UserService userService;


    @GetMapping("/getallusers")
    public List<User> getAllProducts() {
        return userService.findAllUsers();
    }

    @PostMapping("/adduser")
    public String createProduct(@RequestBody User user) {
        userService.saveUser(user);
        return "User Saved Successfully";
    }

    @GetMapping("/userby/{id}")
    public User getProductById(@PathVariable Long id) {
        return userService.findByUserId(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User deleted with id: " + id;
    }

}
