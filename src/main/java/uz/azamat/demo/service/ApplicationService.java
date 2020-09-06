package uz.azamat.demo.service;

import org.springframework.stereotype.Service;
import uz.azamat.demo.model.Application;
import uz.azamat.demo.repository.ApplicationRepository;

@Service
public class ApplicationService {
    ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void saveApplication(Application application){
        applicationRepository.save(application);
    }
}
