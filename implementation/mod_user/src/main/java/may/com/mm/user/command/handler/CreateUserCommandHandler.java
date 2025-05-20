package may.com.mm.user.command.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.component.exception.UMSException;
import may.com.mm.user.command.CreateUserCommand;
import may.com.mm.user.exception.UserErrors;
import may.com.mm.user.model.User;
import may.com.mm.user.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserCommandHandler implements CreateUserCommand {

    private static final Logger LOG = LoggerFactory.getLogger(CreateUserCommandHandler.class);

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Output execute(Input input) throws UMSException {

        if (this.userRepository.findOne(UserRepository.Filters.emailEquals(input.email())).isPresent()) {
            throw new UMSException(UserErrors.EMAIL_EXIT);
        }

        User user = new User(input.email(), input.name());
        this.userRepository.save(user);
        return new Output(user.getId());
    }

}
