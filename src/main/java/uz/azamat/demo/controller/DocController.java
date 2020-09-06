package uz.azamat.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.azamat.demo.model.Application;
import uz.azamat.demo.model.User;
import uz.azamat.demo.service.ApplicationService;
import uz.azamat.demo.service.UserService;

@RestController
public class DocController {
    @Autowired
    UserService userService;
    @Autowired
    ApplicationService applicationService;

    @PostMapping("/saveUser")
    public int saveUser(User user) {
        userService.saveUser(user);
        return user.getId();
    }

    @PostMapping("/saveApplication")
    public int saveUser(@RequestBody Application application) {
        applicationService.saveApplication(application);
        return application.getId();
    }
}
