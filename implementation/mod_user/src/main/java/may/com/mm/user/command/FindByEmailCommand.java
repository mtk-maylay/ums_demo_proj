package may.com.mm.user.command;

import may.com.mm.common.identifier.UserId;
import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;

public interface FindByEmailCommand {

    Output execute(Input input) throws UMSException;

    record Input(Email email) { }

    record Output(UserId userId) { }

}
