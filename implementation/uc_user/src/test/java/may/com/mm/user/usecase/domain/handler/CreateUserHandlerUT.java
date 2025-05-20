package may.com.mm.user.usecase.domain.handler;

import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;
import may.com.mm.user.usecase.UsecaseConfiguration;
import may.com.mm.user.usecase.domain.CreateUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UsecaseConfiguration.class})
public class CreateUserHandlerUT {

    private static final Logger LOG = LoggerFactory.getLogger(CreateUserHandlerUT.class);

    @Autowired
    private CreateUser createUser;

    @Test
    public void createUser() throws UMSException {

        var output = this.createUser.execute(new CreateUser.Input(new Email("may3@gmail.com"), "May Thin", "1234"));
        LOG.info("User Id : <{}>", output.userId().getEntityId());
    }

}
