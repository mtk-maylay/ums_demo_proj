package may.com.mm.component.exception;

public class UMSException extends DomainException {

    public UMSException(ErrorMessage errorMessage) {

        super(errorMessage);
    }

}
