package may.com.mm.user.usecase.domain.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.user.command.GetUserListCommand;
import may.com.mm.user.model.User;
import may.com.mm.user.usecase.domain.GetUserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserListHandler implements GetUserList {

    private static final Logger LOG = LoggerFactory.getLogger(CreateUserHandler.class);

    private final GetUserListCommand getUserListCommand;

    @Override
    public Output execute() {

        var output = this.getUserListCommand.execute();
        List<Output.User> users = new ArrayList<>();
        for (User user : output.users()) {

            users.add(new Output.User(user.getEmail().toString(), user.getName()));
        }
        return new Output(users);
    }

}
