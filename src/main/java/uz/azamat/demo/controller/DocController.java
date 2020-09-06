package uz.azamat.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.azamat.demo.model.Application;
import uz.azamat.demo.model.Comment;
import uz.azamat.demo.model.User;
import uz.azamat.demo.service.ApplicationService;
import uz.azamat.demo.service.CommentService;
import uz.azamat.demo.service.UserService;

import java.util.List;

@RestController
public class DocController {
    UserService userService;
    ApplicationService applicationService;
    CommentService commentService;

    public DocController(UserService userService, ApplicationService applicationService,
                         CommentService commentService) {
        this.userService = userService;
        this.applicationService = applicationService;
        this.commentService = commentService;
    }

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

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = userService.updateUser(user, id);
        return ResponseEntity.ok(updatedUser);
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

    @PutMapping("/updateApplication/{id}")
    public ResponseEntity updateApplication(@PathVariable int id, @RequestBody Application application){
        Application updatedApplication = applicationService.updateApplication(application, id);
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/deleteApplication/{id}")
    public ResponseEntity deleteApplication(@PathVariable int id){
        int i = applicationService.deleteById(id);
        return ResponseEntity.ok(i);
    }

    @PostMapping("/saveComment")
    public int saveUser(@RequestBody Comment comment) {
        commentService.saveComment(comment);
        return comment.getId();
    }

}
