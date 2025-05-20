package may.com.mm.user.usecase.domain.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.common.identifier.CredentialsId;
import may.com.mm.component.exception.UMSException;
import may.com.mm.iam.command.CreateCredentialsCommand;
import may.com.mm.user.command.CreateUserCommand;
import may.com.mm.user.usecase.domain.CreateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserHandler implements CreateUser {

    private static final Logger LOG = LoggerFactory.getLogger(CreateUserHandler.class);

    private final CreateUserCommand createUserCommand;

    private final CreateCredentialsCommand createCredentialsCommand;

    @Override
    public Output execute(Input input) throws UMSException {

        var output = this.createUserCommand.execute(new CreateUserCommand.Input(input.email(), input.name()));

        this.createCredentialsCommand.execute(new CreateCredentialsCommand.Input(new CredentialsId(output.userId().getEntityId()),
                                                                                 input.password()));

        return new Output(output.userId());
    }

}
