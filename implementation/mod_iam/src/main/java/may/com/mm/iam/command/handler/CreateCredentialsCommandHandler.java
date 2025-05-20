package may.com.mm.iam.command.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.iam.command.CreateCredentialsCommand;
import may.com.mm.iam.model.Credentials;
import may.com.mm.iam.model.repository.CredentialsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCredentialsCommandHandler implements CreateCredentialsCommand {

    private static final Logger LOG = LoggerFactory.getLogger(CreateCredentialsCommandHandler.class);

    private final CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public Output execute(Input input) {

        LOG.info("Start - CreateCredentialsCommandHandler : Credentials ID : <{}>", input.credentialsId().getEntityId());

        Credentials credentials = new Credentials(input.credentialsId(), input.password());

        credentials = this.credentialsRepository.save(credentials);

        LOG.info("End - CreateCredentialsCommandHandler");

        return new Output(credentials.getAccessKey(), credentials.getToken());
    }

}
