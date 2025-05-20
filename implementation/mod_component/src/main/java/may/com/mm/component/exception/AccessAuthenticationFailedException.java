package may.com.mm.component.exception;

public class AccessAuthenticationFailedException extends DomainException {

    public AccessAuthenticationFailedException(ErrorMessage errorMessage) {

        super(errorMessage);

    }

}

