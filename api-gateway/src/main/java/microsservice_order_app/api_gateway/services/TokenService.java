package microsservice_order_app.api_gateway.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String JWT_SECRET;

    public void checkToken(final String token) {
        Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token);
    }

    private SecretKey getSignKey() {
        byte[] keys = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keys);
    }
}