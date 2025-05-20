package may.com.mm.user.api.security;

import lombok.RequiredArgsConstructor;
import may.com.mm.component.exception.AccessAuthenticationFailedException;
import may.com.mm.component.exception.ErrorMessage;
import may.com.mm.component.http.CachedBodyHttpServletRequest;
import may.com.mm.component.http.security.HS256JWT;
import may.com.mm.component.spring.security.XAuthHeaderAuthenticator;
import may.com.mm.user.usecase.domain.FindUserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequiredArgsConstructor
public class UserAPIAuthenticator implements XAuthHeaderAuthenticator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAPIAuthenticator.class);

    // private final PrincipalJdbcQueryCommand principalJdbcQueryCommand;

    private final FindUserCredentials findUserCredentials;

    @Override
    public AuthenticationResult authenticate(String xAuthHeader, String xAccessToken,
                                             CachedBodyHttpServletRequest cachedBodyHttpServletRequest)
        throws AccessAuthenticationFailedException {

        var payload = getPayload(cachedBodyHttpServletRequest);

        LOGGER.info("payload : <{}>", payload);

        var output = this.findUserCredentials.execute(new FindUserCredentials.Input(xAuthHeader, xAccessToken));

        if (output.userId() == null) {
            throw new AccessAuthenticationFailedException(ErrorMessage.INVALID_AUTH);
        }

        return new AuthenticationResult(output.userId().toString()
            , xAccessToken, new UserContext(output.userId().getEntityId(), xAccessToken));

    }

    private String getPayload(CachedBodyHttpServletRequest request) {

        return new String(request.getCachedBody(), StandardCharsets.UTF_8);
    }

    private String getJwtToken(String xAuthHeader) throws AccessAuthenticationFailedException {

        if (xAuthHeader == null || !xAuthHeader.startsWith("Bearer ")) {
            throw new AccessAuthenticationFailedException(ErrorMessage.INVALID_AUTH);
        }
        return xAuthHeader.substring(7);
    }

    private void verifyToken(String jwtToken, String payload, String secretKeyStr) throws AccessAuthenticationFailedException {

        SecretKey secretKey = HS256JWT.secretKey(secretKeyStr);
        try {
            boolean verified = HS256JWT.verify(secretKey, jwtToken, Map.of("alg", "HS256", "typ", "JWT"), payload);
            if (!verified) {
                throw new AccessAuthenticationFailedException(ErrorMessage.INVALID_JWT_SIGNATURE);
            }
        } catch (Exception e) {
            throw new AccessAuthenticationFailedException(new ErrorMessage(
                ErrorMessage.INVALID_JWT_SIGNATURE.code(), "JWT verification failed: " + e.getMessage()));
        }
    }

}
