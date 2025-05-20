package may.com.mm.common.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import may.com.mm.component.exception.ErrorMessage;
import may.com.mm.component.exception.InputException;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Email {

    public static final String FORMAT = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @EqualsAndHashCode.Include
    private String value;

    public Email(String value) {

        assert value != null : "Value is required.";

        if (!Pattern.matches(FORMAT, value)) {
            throw new InputException(new ErrorMessage("FORMAT_ERROR", "Invalid email format."));
        }

        this.value = value;
    }

    private static boolean isValidUsername(String username) {

        return Pattern.matches(FORMAT, username);
    }

    @Override
    public String toString() {

        return this.value;
    }

    @Converter
    public static class JpaConverter implements AttributeConverter<Email, String> {

        @Override
        public String convertToDatabaseColumn(Email attribute) {

            return attribute == null ? null : attribute.value;

        }

        @Override
        public Email convertToEntityAttribute(String dbData) {

            return dbData == null ? null : new Email(dbData);

        }

    }

}