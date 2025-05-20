package may.com.mm.user.command;

import may.com.mm.user.model.User;

import java.util.List;

public interface GetUserListCommand {

    Output execute();

    record Output(List<User> users) { }

}
