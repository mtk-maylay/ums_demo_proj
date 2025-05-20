package may.com.mm.iam.command.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.component.exception.UMSException;
import may.com.mm.iam.command.AuthenticateCommand;
import may.com.mm.iam.exception.CredentialsErrors;
import may.com.mm.iam.model.repository.CredentialsRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticateCommandHandler implements AuthenticateCommand {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticateCommandHandler.class);

    private final CredentialsRepository credentialsRepository;

    @Override
    @Transactional
    public Output execute(Input input) throws UMSException {

        LOG.info("Start - AuthenticateCommandHandler for Credentials ID: {}", input.credentialsId().getEntityId());

        // Retrieve Principal by PrincipalId
        var credentials = this.credentialsRepository.findById(input.credentialsId()).orElseThrow(() -> new UMSException(
            CredentialsErrors.USER_INVALID
        ));

        // Validate Password
        if (!credentials.getPasswordHash().equals(DigestUtils.sha256Hex(input.password()))) {
            LOG.error("Invalid password for Credentials ID: {}", input.credentialsId().getEntityId());
            throw new UMSException(CredentialsErrors.PASSWORD_NOT_MATCH);
        }

        credentials.extendToken();
        this.credentialsRepository.save(credentials);

        LOG.info("Authentication successful: <{}>", input.credentialsId().getEntityId());

        return new Output(credentials.getAccessKey(), credentials.getToken());
    }

}
