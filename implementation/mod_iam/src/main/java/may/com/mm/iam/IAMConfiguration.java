package may.com.mm.iam;

import may.com.mm.infra.postgres.DatasourceConfiguration;
import may.com.mm.user.UserConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan("may.com.mm.iam")
@Import(value = {UserConfiguration.class, DatasourceConfiguration.class})
public class IAMConfiguration {
}
