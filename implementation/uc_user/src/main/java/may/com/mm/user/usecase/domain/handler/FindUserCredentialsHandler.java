package may.com.mm.user.usecase.domain.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.common.identifier.CredentialsId;
import may.com.mm.common.identifier.UserId;
import may.com.mm.component.exception.UMSException;
import may.com.mm.iam.command.CreateCredentialsCommand;
import may.com.mm.iam.command.FindByTokenCommand;
import may.com.mm.user.command.CreateUserCommand;
import may.com.mm.user.usecase.domain.CreateUser;
import may.com.mm.user.usecase.domain.FindUserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserCredentialsHandler implements FindUserCredentials {

    private static final Logger LOG = LoggerFactory.getLogger(FindUserCredentialsHandler.class);

    private final FindByTokenCommand findByTokenCommand;

    @Override
    public Output execute(Input input) {

        var output = this.findByTokenCommand.execute(new FindByTokenCommand.Input(input.token(), input.accessKey()));

        return new Output(output.userId() != null ? new UserId(output.userId().getEntityId()) : null);
    }

}
