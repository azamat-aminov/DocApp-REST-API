package uz.azamat.demo.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uz.azamat.demo.token.ParseToken;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class LogAndPassFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String jwtToken = request.getHeader("Authorization");

            ParseToken parseToken = new ParseToken(jwtToken);
            Jws<Claims> jwsClaims = parseToken.parseClaims("secret");
            int id = jwsClaims.getBody().get("userId", Integer.class);
            System.out.println("id: " + id);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(401);
        }
    }
}
