package may.com.mm.user.api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;
import may.com.mm.user.usecase.domain.CreateUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterUserController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterUserController.class);

    private final CreateUser createUser;

    @PostMapping(value = "/public/register_user")
    public ResponseEntity<Response> execute(@RequestBody Request request) throws UMSException {

        var output = this.createUser.execute(new CreateUser.Input(new Email(request.email), request.name, request.password));

        return ResponseEntity.ok(new Response(output.userId().toString()));
    }

    record Request(@NotEmpty @JsonProperty("email") String email,
                   @NotEmpty @JsonProperty("name") String name,
                   @NotEmpty @JsonProperty("password") String password) { }

    record Response(@JsonProperty("user_id") String userId) { }

}
