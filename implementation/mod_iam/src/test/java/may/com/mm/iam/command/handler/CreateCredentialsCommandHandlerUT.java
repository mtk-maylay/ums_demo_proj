package may.com.mm.iam.command.handler;

import may.com.mm.common.identifier.CredentialsId;
import may.com.mm.iam.IAMConfiguration;
import may.com.mm.iam.command.CreateCredentialsCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {IAMConfiguration.class})
public class CreateCredentialsCommandHandlerUT {

    private static final Logger LOG = LoggerFactory.getLogger(CreateCredentialsCommandHandlerUT.class);

    @Autowired
    private CreateCredentialsCommand createCredentialsCommand;

    @Test
    public void createCredentials() {

        var output = this.createCredentialsCommand.execute(new CreateCredentialsCommand.Input(new CredentialsId(1234L), "1234"));

        LOG.info("Token : <{}>", output.token());
    }

}
