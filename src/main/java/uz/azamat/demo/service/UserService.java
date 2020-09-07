package uz.azamat.demo.service;

import org.springframework.stereotype.Service;
import uz.azamat.demo.model.User;
import uz.azamat.demo.model.UserDto;
import uz.azamat.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserDto> getAllUser() {
        List<User> all = userRepository.findAll();
        List<UserDto> list = new ArrayList<>();
        for (User user : all) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setDate(user.getDate());
            userDto.setAddress(user.getAddress());
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());
            list.add(userDto);
        }
        return list;
    }

    public User findByID(int id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user, int id) {
        User updatedUser = userRepository.findById(id);
        updatedUser.setId(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setDate(user.getDate());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setLogin(user.getLogin());
        updatedUser.setPassword(user.getPassword());

        return userRepository.save(updatedUser);
    }
}
