package uz.azamat.demo.token;

import io.jsonwebtoken.*;

public class ParseToken {
    private String token;

    public ParseToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
    }
}
