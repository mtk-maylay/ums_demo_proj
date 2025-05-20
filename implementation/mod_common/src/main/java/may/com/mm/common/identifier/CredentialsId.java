package may.com.mm.common.identifier;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import may.com.mm.component.persistence.jpa.JpaId;

@Embeddable
@NoArgsConstructor
public class CredentialsId extends JpaId<Long> {

    @Column(name = "credentials_id")
    private Long id;

    public CredentialsId(Long id) {

        this.id = id;
    }

    @Override
    public Long getEntityId() {

        return id;
    }

}
