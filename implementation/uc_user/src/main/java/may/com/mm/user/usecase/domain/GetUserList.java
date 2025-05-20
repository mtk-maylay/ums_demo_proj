package may.com.mm.user.usecase.domain;

import java.util.List;

public interface GetUserList {

    Output execute();

    record Output(List<User> users) {

        public record User(String email, String name) { }

    }

}
