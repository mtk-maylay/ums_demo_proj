package may.com.mm.user.exception;

import may.com.mm.component.exception.ErrorMessage;

public class UserErrors {

    //@@formatter:off
    public static final ErrorMessage INVALID_EMAIL_FORMAT = new ErrorMessage("INVALID_EMAIL_FORMAT", "Email is required to be a valid email address.");
    public static final ErrorMessage EMAIL_EXIT = new ErrorMessage("EMAIL_EXIT", "Email already exists!");
    public static final ErrorMessage NOT_FOUND = new ErrorMessage("NOT_FOUND", "User not found!");

    //@@formatter:on
}
