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

    public ApplicationDto updateApplication(Application application, int id) throws Exception {
        Application updatedApplication = applicationRepository.findById(id);
        ApplicationDto applicationDto = new ApplicationDto();
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

        applicationDto.setId(updatedApplication.getId());
        applicationDto.setHeading(updatedApplication.getHeading());
        applicationDto.setText(updatedApplication.getText());
        applicationDto.setDate(updatedApplication.getDate());
        applicationDto.setUserId(updatedApplication.getUser().getId());

        return applicationDto;
    }

    public String deleteById(int id) {
        Application application = applicationRepository.findById(id);
        int userId = application.getUser().getId();
        int currentUserId = getCurrentUser().getId();
        if (userId == currentUserId) {
            applicationRepository.deleteById(id);
            return "User deleted";
        } else {
            return "You did not create this application. So you do not delete it";
        }
    }
}
