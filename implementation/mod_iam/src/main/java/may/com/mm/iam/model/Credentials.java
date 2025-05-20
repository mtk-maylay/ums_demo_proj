package may.com.mm.iam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import may.com.mm.common.identifier.CredentialsId;
import may.com.mm.component.persistence.jpa.JpaEntity;
import may.com.mm.component.persistence.jpa.JpaInstantConverter;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
@Entity
@Table(name = "ums_credentials")
@NoArgsConstructor
@ToString
public class Credentials extends JpaEntity<CredentialsId> {

    @EmbeddedId
    private CredentialsId credentialsId;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "access_key")
    private String accessKey;

    @Column(name = "token")
    private String token;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "expired_at")
    @Convert(converter = JpaInstantConverter.class)
    private Instant expiredAt;

    public Credentials(CredentialsId credentialsId,
                       String passwordHash) {

        this.credentialsId = credentialsId;
        this.secretKey = UUID.randomUUID().toString();
        this.accessKey = UUID.randomUUID().toString();
        this.token = DigestUtils.sha256Hex(UUID.randomUUID().toString());
        this.passwordHash = DigestUtils.sha256Hex(passwordHash);
        this.expiredAt = Instant.now().plus(5, ChronoUnit.MINUTES);
    }

    public boolean isExpired() {

        return this.expiredAt.isBefore(Instant.now());
    }

    public void extendToken() {

        this.expiredAt = Instant.now().plus(30, ChronoUnit.MINUTES);
    }

    @Override
    public CredentialsId getId() {

        return this.credentialsId;
    }

}
