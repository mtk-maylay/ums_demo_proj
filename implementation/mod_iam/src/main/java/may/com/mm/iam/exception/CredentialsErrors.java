package may.com.mm.iam.exception;

import may.com.mm.component.exception.ErrorMessage;

public class CredentialsErrors {

    //@@formatter:off
    public static final ErrorMessage USER_INVALID = new ErrorMessage("USER_INVALID",
                                                                     "User is invalid.");

    public static final ErrorMessage PASSWORD_NOT_MATCH = new ErrorMessage("PASSWORD_NOT_MATCH",
                                                                           "Password not matched.");
    //@@formatter:on
}
