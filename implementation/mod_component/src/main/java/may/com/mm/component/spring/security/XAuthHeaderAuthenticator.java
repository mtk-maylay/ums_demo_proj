package may.com.mm.component.spring.security;

import may.com.mm.component.http.CachedBodyHttpServletRequest;
import may.com.mm.component.exception.AccessAuthenticationFailedException;

public interface XAuthHeaderAuthenticator {

    AuthenticationResult authenticate(String xAuthHeader,
                                      String xAccessToken,
                                      CachedBodyHttpServletRequest cachedBodyHttpServletRequest) throws AccessAuthenticationFailedException;

    record AuthenticationResult(String principalId, String credentials, Object details) { }

    class AuthenticationFailureException extends RuntimeException {

        public AuthenticationFailureException(String message) {

            super(message);
        }

    }

}
