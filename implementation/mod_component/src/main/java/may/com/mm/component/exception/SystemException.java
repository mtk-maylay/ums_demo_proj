package may.com.mm.component.exception;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class SystemException extends RuntimeException {

    @Getter(AccessLevel.PUBLIC)
    private final ErrorMessage errorMessage;

    public SystemException(ErrorMessage errorMessage) {

        super(errorMessage.code() + "-" + errorMessage.description());
        this.errorMessage = errorMessage;
    }

    public SystemException() {

        super();
        this.errorMessage = null;
    }

}