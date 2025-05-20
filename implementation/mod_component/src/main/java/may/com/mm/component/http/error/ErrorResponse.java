package may.com.mm.component.http.error;

public record ErrorResponse(String code, String message) {

    public interface ErrorResponseBuilder {

        ErrorResponse build(Exception exception);

    }

}