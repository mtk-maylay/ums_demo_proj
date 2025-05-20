package may.com.mm.user;

import may.com.mm.component.ComponentConfiguration;
import may.com.mm.infra.postgres.DatasourceConfiguration;
import may.com.mm.infra.postgres.PostgresConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("may.com.mm.user")
@Import(value = {DatasourceConfiguration.class, ComponentConfiguration.class})
public class UserConfiguration {

}
