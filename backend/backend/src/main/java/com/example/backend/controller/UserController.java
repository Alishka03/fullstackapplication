package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import com.example.backend.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/users")
@CrossOrigin("http://localhost:3000/")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hi")
    public String sayHi() {
        return "Hello";
    }

    @GetMapping("/map")
    public HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "John");
        map.put("email", "alisher");
        map.put("user", "user");
        return map;
    }

    @GetMapping("")
    public List<User> allUsers() {
        return userService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> savingUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        if (userService.getUserById(id).isEmpty()) {
           throw new UserNotFoundException(id);
        } else {
            userService.deleteUser(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (userService.getUserById(id).isEmpty()) {
            return new ResponseEntity<String>("Not found!", HttpStatus.NOT_FOUND);
        } else {
            userService.editUser(user, id);
            return new ResponseEntity<String>("Done!", HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}