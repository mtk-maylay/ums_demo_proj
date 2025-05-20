package may.com.mm.user.api;

import may.com.mm.user.usecase.UsecaseConfiguration;
import org.springframework.context.annotation.Import;

@Import(
    value = {
        UsecaseConfiguration.class,
        WebConfiguration.class,
        WebSecurityConfiguration.class})
public class ApplicationConfiguration { }
