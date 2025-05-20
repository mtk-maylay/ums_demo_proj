package may.com.mm.user.usecase;

import may.com.mm.component.ComponentConfiguration;
import may.com.mm.iam.IAMConfiguration;
import may.com.mm.infra.postgres.DatasourceConfiguration;
import may.com.mm.user.UserConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan("may.com.mm.user.usecase")
@Import(value = {DatasourceConfiguration.class, ComponentConfiguration.class, UserConfiguration.class, IAMConfiguration.class})
public class UsecaseConfiguration {
}
