package com.ecomm.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private  UserService userService;


    //@GetMapping("/getallusers")
    @RequestMapping(value = "getallusers" ,method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllProducts() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> createProduct(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>("User added Successfully", HttpStatus.OK);
    }

    @GetMapping("/userby/{id}")
    public ResponseEntity<User> getProductById(@PathVariable Long id) {
        Optional<User> user=userService.findByUserId(id);
            if(user==null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(user.get(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id , @RequestBody User user){
        boolean updated= userService.updateuser(id,user);
        if(updated) return new ResponseEntity<>("User Updated ",HttpStatus.OK);
        return new ResponseEntity<>("User NOT Found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "User deleted with id: " + id;
    }

}
