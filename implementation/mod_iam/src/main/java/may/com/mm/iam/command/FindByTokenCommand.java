package may.com.mm.iam.command;

import may.com.mm.common.identifier.CredentialsId;
import may.com.mm.common.identifier.UserId;
import may.com.mm.user.model.User;

import java.io.ObjectOutput;

public interface FindByTokenCommand {

    Output execute(Input input);

    record Input(String token, String accessKey) { }

    record Output(CredentialsId userId) { }

}
