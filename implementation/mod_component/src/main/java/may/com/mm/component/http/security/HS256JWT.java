package may.com.mm.component.http.security;

import io.jsonwebtoken.Jwts;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;

public final class HS256JWT {

    private static final Logger LOGGER = LoggerFactory.getLogger(HS256JWT.class);

    private HS256JWT() { }

    public static SecretKey secretKey(String secretKey) {

        return new SecretKeySpec(DigestUtils.sha256(secretKey), 0, 32, "HMACSHA256");
    }

    public static Token sign(SecretKey secretKey, Map<String, Object> headers, String payload) {

        String token = Jwts.builder()
                           .header().add(headers).and().content(payload, "application/json")
                           .signWith(secretKey, Jwts.SIG.HS256).compact();

        String[] parts = token.split("\\.");

        return new Token(parts[0], parts[1], parts[2], token);
    }

    public static boolean verify(SecretKey secretKey, String token, Map<String, Object> headers, String payload) {

        return token.equals(HS256JWT.sign(secretKey, headers, payload).full);
    }

    public record Token(String header, String body, String signature, String full) { }

}
