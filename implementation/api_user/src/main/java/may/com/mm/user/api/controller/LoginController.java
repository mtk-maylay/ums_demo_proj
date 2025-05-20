package may.com.mm.user.api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import may.com.mm.common.type.Email;
import may.com.mm.component.exception.UMSException;
import may.com.mm.user.usecase.domain.Authenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private final Authenticate authenticate;

    @PostMapping(value = "/public/login")
    public ResponseEntity<Response> execute(@RequestBody Request request) throws UMSException {

        var output = this.authenticate.execute(new Authenticate.Input(new Email(request.email), request.password));

        return ResponseEntity.ok(new Response(output.accessKey(), output.token()));
    }

    record Request(@JsonProperty("email") String email, @JsonProperty("password") String password) { }

    record Response(@JsonProperty("access_key") String accessKey, @JsonProperty("token") String token) { }

}
