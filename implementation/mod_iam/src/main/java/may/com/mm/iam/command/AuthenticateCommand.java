package may.com.mm.iam.command;

import may.com.mm.common.identifier.CredentialsId;
import may.com.mm.component.exception.UMSException;

public interface AuthenticateCommand {

    Output execute(Input input) throws UMSException;

    record Input(CredentialsId credentialsId, String password) { }

    record Output(String accessKey, String token) { }

}
