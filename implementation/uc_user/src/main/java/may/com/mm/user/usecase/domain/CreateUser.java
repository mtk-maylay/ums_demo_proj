package may.com.mm.user.usecase.domain;

import may.com.mm.common.identifier.UserId;
import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;

public interface CreateUser {

    Output execute(Input input) throws UMSException;

    record Input(Email email, String name, String password) { }

    record Output(UserId userId) { }

}
