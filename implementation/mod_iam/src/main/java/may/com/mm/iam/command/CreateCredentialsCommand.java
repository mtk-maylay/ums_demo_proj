package may.com.mm.iam.command;

import may.com.mm.common.identifier.CredentialsId;

public interface CreateCredentialsCommand {

    Output execute(Input input);

    record Input(CredentialsId credentialsId, String password) { }

    record Output(String accessKey, String token) { }

}
