package may.com.mm.user.command;

import may.com.mm.common.identifier.UserId;
import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;

public interface CreateUserCommand {

    Output execute(Input input) throws UMSException;

    record Input(Email email, String name) { }

    record Output(UserId userId) { }

}
