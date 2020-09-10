package uz.azamat.demo.service;

import org.springframework.stereotype.Service;
import uz.azamat.demo.model.User;
import uz.azamat.demo.model.UserDto;
import uz.azamat.demo.repository.UserRepository;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static uz.azamat.demo.filter.LogAndPassFilter.getCurrentUser;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) throws NoSuchAlgorithmException {
        String password = user.getPassword();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        String hashedOutput = DatatypeConverter.printHexBinary(digest);
        user.setPassword(hashedOutput);
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

    public User updateUser(User user, int id) throws NoSuchAlgorithmException {
        User updatedUser = userRepository.findById(id);
        updatedUser.setId(id);
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setDate(user.getDate());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setLogin(user.getLogin());
        String password = user.getPassword();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        String hashedOutput = DatatypeConverter.printHexBinary(digest);
        updatedUser.setPassword(hashedOutput);

        int userId = updatedUser.getId();
        int currentUserId = getCurrentUser().getId();
        if (userId == currentUserId) {
            return userRepository.save(updatedUser);
        } else {
            return null;
        }
    }
}
