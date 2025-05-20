package may.com.mm.component.exception;

public abstract class IgnorableException extends DomainException {

    public IgnorableException() {

        super();
    }

    public IgnorableException(ErrorMessage errorMessage) {

        super(errorMessage);
    }

}
