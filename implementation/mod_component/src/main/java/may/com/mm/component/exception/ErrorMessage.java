package may.com.mm.component.exception;

public record ErrorMessage(String code, String description) {

    //@@formatter:off
    public static final ErrorMessage INVALID_AUTH = new ErrorMessage("INVALID_AUTH", "Invalid Authorization.");
    public static final ErrorMessage MALFORMED_JWT_TOKEN = new ErrorMessage("MALFORMED_JWT_TOKEN", "Malformed JWT token.");
    public static final ErrorMessage INVALID_ACCESS_KEY = new ErrorMessage("INVALID_ACCESS_KEY", "Invalid Access Key.");
    public static final ErrorMessage FAILED_DECODE_JWT = new ErrorMessage("FAILED_DECODE_JWT", "");
    public static final ErrorMessage INVALID_JWT_SIGNATURE = new ErrorMessage("INVALID_JWT_SIGNATURE", "JWT signature is invalid.");
    //@@formatter:on

}