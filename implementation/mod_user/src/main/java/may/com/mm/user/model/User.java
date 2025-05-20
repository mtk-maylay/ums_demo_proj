package may.com.mm.user.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import may.com.mm.common.identifier.UserId;
import may.com.mm.common.type.Email;
import may.com.mm.component.exception.InputException;
import may.com.mm.component.persistence.jpa.JpaEntity;
import may.com.mm.component.utility.Snowflake;
import may.com.mm.user.exception.UserErrors;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
@ToString
public class User extends JpaEntity<UserId> {

    @EmbeddedId
    private UserId userId;

    @Column(name = "email")
    @Convert(converter = Email.JpaConverter.class)
    private Email email;

    @Column(name = "name")
    private String name;

    public User(Email email, String name) {

        if (email == null) {

            throw new InputException(UserErrors.INVALID_EMAIL_FORMAT);
        }

        this.userId = new UserId(Snowflake.get().nextId());
        this.email = email;
        this.name = name;
    }

    @Override
    public UserId getId() {

        return this.userId;
    }

}
