package uz.azamat.demo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
@RequestMapping("/docs")
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
    @ApiOperation(value = "register new User")
    public int saveUser(@RequestBody User user) throws NoSuchAlgorithmException {
        userService.saveUser(user);
        return user.getId();
    }

    @GetMapping("/getAllUsers")
    @ApiOperation(value = "get all Users", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity getAllUsers() {
        List<UserDto> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PutMapping("/updateUser/{id}")
    @ApiOperation(value = "update User", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody User user) throws Exception {
        UserDto updatedUser = userService.updateUser(user, id);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/saveApplication")
    @ApiOperation(value = "register new Application", authorizations = {@Authorization(value = "jwtToken")})
    public int saveUser(@RequestBody Application application) {
        applicationService.saveApplication(application);
        return application.getId();
    }

    @GetMapping("/getAllApplications")
    @ApiOperation(value = "get all Applications", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity getAllApplications() {
        List<ApplicationDto> allApplications = applicationService.getAllApplications();
        return ResponseEntity.ok(allApplications);
    }

    @PutMapping("/updateApplication/{id}")
    @ApiOperation(value = "update Applications", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity updateApplication(@PathVariable int id, @RequestBody ApplicationDto application) throws Exception {
        ApplicationDto updatedApplicationDto = applicationService.updateApplication(application, id);
        return ResponseEntity.ok(updatedApplicationDto);
    }

    @DeleteMapping("/deleteApplication/{id}")
    @ApiOperation(value = "delete Application by ID", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity deleteApplication(@PathVariable int id) {
        String s = applicationService.deleteById(id);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/saveComment/{id}")
    @ApiOperation(value = "register new Comment", authorizations = {@Authorization(value = "jwtToken")})
    public int saveUser(@PathVariable int id, @RequestBody Comment comment) {
        commentService.saveComment(comment, id);
        return comment.getId();
    }

    @PutMapping("/updateComment/{id}")
    @ApiOperation(value = "update Comment", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity updateComment(@PathVariable int id, @RequestBody CommentDto comment) throws Exception {
        CommentDto updatedComment = commentService.updateComment(comment, id);
        return ResponseEntity.ok(updatedComment);
    }

    @GetMapping("/getAllComments")
    @ApiOperation(value = "get all Comments", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity getAllComments() {
        List<CommentDto> allComments = commentService.getAllComments();
        return ResponseEntity.ok(allComments);
    }

    @PostMapping("/checkLoginAndPassword")
    @ApiOperation(value = "check login and password")
    public String checkLoginAndPassword(@RequestBody LoginPassword lp) throws NoSuchAlgorithmException {
        return loginPasswordService.checkLogPass(lp.getLogin(), lp.getPassword());
    }

    @DeleteMapping("/deleteComment/{id}")
    @ApiOperation(value = "delete Comment by ID", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity deleteComment(@PathVariable int id) {
        String s = commentService.deleteById(id);
        return ResponseEntity.ok(s);
    }
}
