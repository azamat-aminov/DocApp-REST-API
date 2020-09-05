package uz.azamat.demo.service;

import org.springframework.stereotype.Service;
import uz.azamat.demo.model.User;
import uz.azamat.demo.repository.UserRepository;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
          userRepository.save(user);
    }

}
