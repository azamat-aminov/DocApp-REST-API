package uz.azamat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.azamat.demo.model.Application;
import uz.azamat.demo.model.User;
import uz.azamat.demo.service.ApplicationService;
import uz.azamat.demo.service.UserService;

import java.util.List;

@RestController
public class DocController {
    @Autowired
    UserService userService;
    @Autowired
    ApplicationService applicationService;

    @PostMapping("/saveUser")
    public int saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return user.getId();
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PostMapping("/saveApplication")
    public int saveUser(@RequestBody Application application) {
        applicationService.saveApplication(application);
        return application.getId();
    }

    @GetMapping("/getAllApplications")
    public ResponseEntity getAllApplications() {
        List<Application> allApplications = applicationService.getAllApplications();
        return ResponseEntity.ok(allApplications);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = userService.updateUser(user, id);
        return ResponseEntity.ok(updatedUser);
    }
}
