package uz.azamat.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.azamat.demo.model.User;
import uz.azamat.demo.service.UserService;

@RestController
public class DocController {
    UserService userService;

    public DocController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public int saveUser(User user) {
        userService.saveUser(user);
        return user.getId();
    }
}
