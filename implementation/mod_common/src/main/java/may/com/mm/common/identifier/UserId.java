package may.com.mm.common.identifier;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import may.com.mm.component.persistence.jpa.JpaId;

@Embeddable
@NoArgsConstructor
public class UserId extends JpaId<Long> {

    @Column(name = "id")
    private Long id;

    public UserId(Long id) {

        this.id = id;
    }

    @Override
    public Long getEntityId() {

        return id;
    }

}
