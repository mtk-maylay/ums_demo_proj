package may.com.mm.iam.command.handler;

import lombok.RequiredArgsConstructor;
import may.com.mm.iam.command.FindByTokenCommand;
import may.com.mm.iam.model.Credentials;
import may.com.mm.iam.model.repository.CredentialsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindByTokenCommandHandler implements FindByTokenCommand {

    private final CredentialsRepository credentialsRepository;

    @Override
    public Output execute(Input input) {

        var credentials = this.credentialsRepository.findOne(CredentialsRepository.Filters.tokenEquals(input.token())
                                                                                          .and(CredentialsRepository.Filters.accessKeyEquals(
                                                                                              input.accessKey())));
        return new Output(credentials.map(Credentials::getId).orElse(null));
    }

}
