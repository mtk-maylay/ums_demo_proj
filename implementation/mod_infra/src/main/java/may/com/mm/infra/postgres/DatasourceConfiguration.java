package may.com.mm.infra.postgres;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Configuration
//@PropertySource("classpath:application-dev.properties")
@Import(value = {PostgresConfiguration.class})
@RequiredArgsConstructor
public class DatasourceConfiguration {

    private final Environment env;

    @Bean
    public PostgresSetting defaultPostgresSettings() throws IOException {

//        InputStream in = getClass().getClassLoader().getResourceAsStream("application-dev.properties");
//        if (in == null) {
//            throw new RuntimeException("application-dev.properties not found in classpath");
//        }
//        Properties props = new Properties();
//        props.load(in);
//        System.out.println("Manual Load: " + props.getProperty("jdbc.db.username"));
//        System.out.println(this.env.getRequiredProperty("jdbc.hibernate.default_schema"));

        return new DefaultPostgresSetting(this.env.getProperty("jdbc.db.url"),
                                          this.env.getProperty("jdbc.db.username"),
                                          this.env.getProperty("jdbc.db.password"),
                                          this.env.getProperty("jdbc.db.driver"),
                                          Integer.valueOf(Objects.requireNonNull(this.env.getProperty("jdbc.db.poolSize"))),
                                          this.env.getProperty("jdbc.hibernate.dialect"),
                                          Boolean.valueOf(this.env.getProperty("jdbc.hibernate.show_sql")),
                                          Boolean.valueOf(this.env.getProperty("jdbc.hibernate.format_sql")),
                                          this.env.getProperty("jdbc.hibernate.default_schema"));

    }

}
