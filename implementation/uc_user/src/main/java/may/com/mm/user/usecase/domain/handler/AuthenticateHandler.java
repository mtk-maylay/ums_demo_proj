package may.com.mm.user.usecase.domain.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.common.identifier.CredentialsId;
import may.com.mm.component.exception.UMSException;
import may.com.mm.iam.command.AuthenticateCommand;
import may.com.mm.iam.command.CreateCredentialsCommand;
import may.com.mm.user.command.CreateUserCommand;
import may.com.mm.user.command.FindByEmailCommand;
import may.com.mm.user.usecase.domain.Authenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateHandler implements Authenticate {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticateHandler.class);

    private final FindByEmailCommand findByEmailCommand;

    private final AuthenticateCommand authenticateCommand;

    @Override
    public Output execute(Input input) throws UMSException {

        var output = this.findByEmailCommand.execute(new FindByEmailCommand.Input(input.email()));

        var authenticateOut = this.authenticateCommand.execute(new AuthenticateCommand.Input(new CredentialsId(output.userId()
                                                                                                                     .getEntityId()),
                                                                                             input.password()));
        return new Output(authenticateOut.accessKey(), authenticateOut.token());
    }

}
