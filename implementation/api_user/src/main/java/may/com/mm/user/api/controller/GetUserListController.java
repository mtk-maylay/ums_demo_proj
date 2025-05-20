package may.com.mm.user.api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import may.com.mm.user.usecase.domain.GetUserList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetUserListController {

    private final GetUserList getUserList;

    @GetMapping(value = "/secured/users")
    public ResponseEntity<Response> execute() {

        var output = this.getUserList.execute();

        List<Response.User> users = new ArrayList<>();
        for (GetUserList.Output.User user : output.users()) {

            users.add(new Response.User(user.email().toString(), user.name()));
        }

        return ResponseEntity.ok(new Response(users));
    }

    record Response(@JsonProperty("users") List<User> users) {

        public record User(String email, String name) { }

    }

}
