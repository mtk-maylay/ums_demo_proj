package may.com.mm.component.http.security;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

public final class RS256JWT {

    private static final Logger LOGGER = LoggerFactory.getLogger(RS256JWT.class);

    private RS256JWT() { }

    private static String removeHeaders(String pem) {

        return pem.replaceAll("-----BEGIN (.*)-----", "")
                   .replaceAll("-----END (.*)-----", "")
                   .replaceAll("\\s", "");
    }

    public static Token sign(PrivateKey privateKey,
                             Map<String, Object> headers,
                             String payload) {

        String token = Jwts.builder()
                           .signWith(privateKey)
                           .header()
                           .add(headers)
                           .and()
                           .content(
                               payload, "application/json")
                           .compact();

        String[] parts = token.split("\\.");

        return new Token(parts[0], parts[1], parts[2], token);
    }

    public static boolean verify(PublicKey publicKey, String token, String payload) {

        try {

            var content = new String(Jwts.parser()
                                         .verifyWith(publicKey)
                                         .build()
                                         .parseSignedContent(token)
                                         .getPayload(),
                                     StandardCharsets.UTF_8);

            return content.equals(payload);

        } catch (Exception e) {

            LOGGER.error("Error : {0}", e);
            return false;
        }
    }

    public static boolean verify(String publicKeyPem, String token, String payload) {

        try {

            String publicKeyContent = RS256JWT.removeHeaders(publicKeyPem);

            // Decode Base64 encoded public key
            byte[] publicKeyBytes = java.util.Base64.getDecoder()
                                                    .decode(publicKeyContent);

            // Create a PublicKey object
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Or whichever algorithm you're using
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            var content = new String(Jwts.parser()
                                         .verifyWith(publicKey)
                                         .build()
                                         .parseSignedContent(token)
                                         .getPayload(),
                                     StandardCharsets.UTF_8);

            return content.equals(payload);

        } catch (Exception e) {

            LOGGER.error("Error : {0}", e);
            return false;
        }
    }

    public record Token(String header,
                        String body,
                        String signature,
                        String full) { }

}
