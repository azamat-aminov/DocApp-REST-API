package uz.azamat.demo.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import uz.azamat.demo.model.User;
import uz.azamat.demo.repository.UserRepository;

import java.util.Date;

public class GenerateToken {
    UserRepository userRepository;
    private String secret = "secret";

    public GenerateToken(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getToken(String login) {
        User user = userRepository.findByLogin(login);
        System.out.println("user--------" + user);
        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("DocApp")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }
}
