package may.com.mm.user.usecase.domain;

import may.com.mm.common.identifier.UserId;
import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;

public interface FindUserCredentials {

    Output execute(Input input);

    record Input(String token, String accessKey) { }

    record Output(UserId userId) { }

}
