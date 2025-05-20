package may.com.mm.user.command.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.component.exception.UMSException;
import may.com.mm.user.command.FindByEmailCommand;
import may.com.mm.user.exception.UserErrors;
import may.com.mm.user.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindByEmailCommandHandler implements FindByEmailCommand {

    private static final Logger LOG = LoggerFactory.getLogger(FindByEmailCommandHandler.class);

    private final UserRepository userRepository;

    @Override
    public Output execute(Input input) throws UMSException {

        var user = this.userRepository.findOne(UserRepository.Filters.emailEquals(input.email())).orElseThrow(() -> new UMSException(
            UserErrors.NOT_FOUND));

        return new Output(user.getId());
    }

}
