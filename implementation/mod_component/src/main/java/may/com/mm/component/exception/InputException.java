package may.com.mm.component.exception;

import lombok.AccessLevel;
import lombok.Getter;

public class InputException extends RuntimeException {

    @Getter(AccessLevel.PUBLIC)
    private final ErrorMessage errorMessage;

    public InputException(ErrorMessage errorMessage) {

        super(errorMessage.code() + "-" + errorMessage.description());
        this.errorMessage = errorMessage;
    }

    public InputException() {

        super();
        this.errorMessage = null;
    }

}