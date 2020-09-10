package uz.azamat.demo.service;

import org.springframework.stereotype.Service;
import uz.azamat.demo.model.User;
import uz.azamat.demo.repository.UserRepository;
import uz.azamat.demo.token.GenerateToken;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class LoginPasswordService {
    UserRepository userRepository;

    public LoginPasswordService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String checkLogPass(String login, String password) throws NoSuchAlgorithmException {
        GenerateToken generateToken = new GenerateToken(userRepository);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] digest = messageDigest.digest();
        String hashedPassword = DatatypeConverter.printHexBinary(digest);
        List<User> all = userRepository.findAll();
        for (User user : all){
            String login2 = user.getLogin();
            String password2 = user.getPassword();
            if (login2.equals(login) && password2.equals(hashedPassword)){
                return generateToken.getToken(login2);
            }
        }
        return "Incorrect login or password";
    }
}
