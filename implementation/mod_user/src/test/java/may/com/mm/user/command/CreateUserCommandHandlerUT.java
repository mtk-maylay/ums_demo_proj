package may.com.mm.user.command;

import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;
import may.com.mm.user.UserConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserConfiguration.class})
public class CreateUserCommandHandlerUT {

    private static final Logger LOG = LoggerFactory.getLogger(CreateUserCommandHandlerUT.class);

    @Autowired
    private CreateUserCommand createUserCommand;

    @Test
    public void createUser() throws UMSException, IOException {


        InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties");
        if (in == null) {
            throw new RuntimeException("application-dev.properties not found in classpath");
        }
        Properties props = new Properties();
        props.load(in);
        //System.out.println("Manual Load: " + props.getProperty("jdbc.db.url"));
        var output = this.createUserCommand.execute(new CreateUserCommand.Input(new Email("maythin@gmail.com"), "May Thin"));
        LOG.info("User Id : <{}>", output.userId().getEntityId());
    }

}
