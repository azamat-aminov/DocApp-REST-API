package uz.azamat.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.azamat.demo.model.*;
import uz.azamat.demo.service.ApplicationService;
import uz.azamat.demo.service.CommentService;
import uz.azamat.demo.service.LoginPasswordService;
import uz.azamat.demo.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class DocController {
    UserService userService;
    ApplicationService applicationService;
    CommentService commentService;
    LoginPasswordService loginPasswordService;

    public DocController(UserService userService, ApplicationService applicationService,
                         CommentService commentService, LoginPasswordService loginPasswordService) {
        this.userService = userService;
        this.applicationService = applicationService;
        this.commentService = commentService;
        this.loginPasswordService = loginPasswordService;
    }

    @PostMapping("/saveUser")
    public int saveUser(@RequestBody User user) throws NoSuchAlgorithmException {
        userService.saveUser(user);
        return user.getId();
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUsers() {
        List<UserDto> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) throws NoSuchAlgorithmException {
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
        List<ApplicationDto> allApplications = applicationService.getAllApplications();
        return ResponseEntity.ok(allApplications);
    }

    @PutMapping("/updateApplication/{id}")
    public ResponseEntity updateApplication(@PathVariable int id, @RequestBody Application application) throws Exception {
        ApplicationDto updatedApplicationDto = applicationService.updateApplication(application, id);
        return ResponseEntity.ok(updatedApplicationDto);
    }

    @DeleteMapping("/deleteApplication/{id}")
    public ResponseEntity deleteApplication(@PathVariable int id) {
        String s = applicationService.deleteById(id);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/saveComment/{id}")
    public int saveUser(@PathVariable int id, @RequestBody Comment comment) {
        commentService.saveComment(comment, id);
        return comment.getId();
    }

    @PutMapping("/updateComment/{id}")
    public ResponseEntity updateComment(@PathVariable int id, @RequestBody Comment comment) throws Exception {
        CommentDto updatedComment = commentService.updateComment(comment, id);
        return ResponseEntity.ok(updatedComment);
    }

    @GetMapping("/getAllComments")
    public ResponseEntity getAllComments() {
        List<CommentDto> allComments = commentService.getAllComments();
        return ResponseEntity.ok(allComments);
    }

    @PostMapping("/checkLoginAndPassword")
    public String checkLoginAndPassword(@RequestBody LoginPassword lp) throws NoSuchAlgorithmException {
        return loginPasswordService.checkLogPass(lp.getLogin(), lp.getPassword());
    }
    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity deleteComment(@PathVariable int id) {
        String s = commentService.deleteById(id);
        return ResponseEntity.ok(s);
    }
}
