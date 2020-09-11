package uz.azamat.demo.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uz.azamat.demo.model.User;
import uz.azamat.demo.repository.UserRepository;
import uz.azamat.demo.token.ParseToken;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class LogAndPassFilter implements Filter {

    UserRepository userRepository;
    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public LogAndPassFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String path = request.getRequestURI();
            if (!path.startsWith("/docs") || "/docs/checkLoginAndPassword".equals(path)
                    || "/docs/saveUser".equals(path)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            String jwtToken = request.getHeader("Authorization");
            ParseToken parseToken = new ParseToken(jwtToken);
            Jws<Claims> jwsClaims = parseToken.parseClaims("secret");
            int id = jwsClaims.getBody().get("userId", Integer.class);
            User user = userRepository.findById(id);
            currentUser.set(user);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(401);
        }
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }
}
