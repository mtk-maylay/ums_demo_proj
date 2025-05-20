package may.com.mm.iam.model.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import may.com.mm.common.identifier.CredentialsId;
import may.com.mm.common.type.Email;
import may.com.mm.iam.model.Credentials;
import may.com.mm.iam.model.QCredentials;
import may.com.mm.user.model.QUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CredentialsRepository extends JpaRepository<Credentials, CredentialsId>, QuerydslPredicateExecutor<Credentials> {

    class Filters {

        public static BooleanExpression tokenEquals(String token) {

            return QCredentials.credentials.token.eq(token);
        }

        public static BooleanExpression accessKeyEquals(String accessKey) {

            return QCredentials.credentials.accessKey.eq(accessKey);
        }

    }

}
