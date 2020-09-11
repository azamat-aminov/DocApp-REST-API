package uz.azamat.demo.service;

import org.springframework.stereotype.Service;
import uz.azamat.demo.model.Application;
import uz.azamat.demo.model.ApplicationDto;
import uz.azamat.demo.model.User;
import uz.azamat.demo.repository.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;

import static uz.azamat.demo.filter.LogAndPassFilter.getCurrentUser;

@Service
public class ApplicationService {
    ApplicationRepository applicationRepository;
    UserService userService;

    public ApplicationService(ApplicationRepository applicationRepository, UserService userService) {
        this.applicationRepository = applicationRepository;
        this.userService = userService;
    }

    public void saveApplication(Application application) {
        User user = getCurrentUser();
        if (user != null) {
            application.setUser(user);
            applicationRepository.save(application);
        }
    }

    public List<ApplicationDto> getAllApplications() {
        List<Application> all = applicationRepository.findAll();
        List<ApplicationDto> list = new ArrayList<>();
        for (Application application : all) {
            ApplicationDto applicationDto = new ApplicationDto();
            applicationDto.setId(application.getId());
            applicationDto.setHeading(application.getHeading());
            applicationDto.setText(application.getText());
            applicationDto.setDate(application.getDate());
            applicationDto.setUserId(application.getUser().getId());
            list.add(applicationDto);
        }
        return list;
    }

    public ApplicationDto updateApplication(ApplicationDto application, int id) throws Exception {
        Application updatedApplication = applicationRepository.findById(id);
        updatedApplication.setId(id);
        updatedApplication.setHeading(application.getHeading());
        updatedApplication.setText(application.getText());
        updatedApplication.setDate(application.getDate());
        int userId = updatedApplication.getUser().getId();
        int currentUserId = getCurrentUser().getId();
        if (userId == currentUserId) {
            updatedApplication = applicationRepository.save(updatedApplication);
        } else {
            throw new Exception("You did not create this application. So you do not update it");
        }

        application.setId(updatedApplication.getId());
        application.setHeading(updatedApplication.getHeading());
        application.setText(updatedApplication.getText());
        application.setDate(updatedApplication.getDate());
        application.setUserId(updatedApplication.getUser().getId());

        return application;
    }

    public String deleteById(int id) {
        Application application = applicationRepository.findById(id);
        int userId = application.getUser().getId();
        int currentUserId = getCurrentUser().getId();
        if (userId == currentUserId) {
            applicationRepository.deleteById(id);
            return "Application deleted";
        } else {
            return "You did not create this application. So you do not delete it";
        }
    }
}
