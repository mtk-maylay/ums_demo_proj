package may.com.mm.infra.vault;

import org.springframework.context.annotation.Bean;

public class VaultConfiguration {

    @Bean
    public Vault vault(Settings settings) {

        return new Vault(settings.address, settings.token, settings.enginePath);
    }

    public record Settings(String address, String token, String enginePath) { }

}