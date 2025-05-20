package may.com.mm.user.model.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import may.com.mm.common.identifier.UserId;
import may.com.mm.common.type.Email;
import may.com.mm.user.model.QUser;
import may.com.mm.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<User, UserId>, QuerydslPredicateExecutor<User> {

    class Filters {

        public static BooleanExpression emailEquals(Email email) {

            return QUser.user.email.eq(email);
        }

    }

}
