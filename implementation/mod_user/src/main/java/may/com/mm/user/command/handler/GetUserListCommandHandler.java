package may.com.mm.user.command.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.user.command.GetUserListCommand;
import may.com.mm.user.model.User;
import may.com.mm.user.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserListCommandHandler implements GetUserListCommand {

    private static final Logger LOG = LoggerFactory.getLogger(FindByEmailCommandHandler.class);

    private final UserRepository userRepository;

    @Override
    public Output execute() {

        List<User> users = this.userRepository.findAll();

        return new Output(users);
    }

}
