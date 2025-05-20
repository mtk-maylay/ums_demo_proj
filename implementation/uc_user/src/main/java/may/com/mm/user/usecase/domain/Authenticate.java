package may.com.mm.user.usecase.domain;

import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;

public interface Authenticate {

    Output execute(Input input) throws UMSException;

    record Input(Email email, String password) { }

    record Output(String accessKey, String token) { }

}
