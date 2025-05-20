package may.com.mm.infra.postgres;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class DefaultPostgresSetting implements PostgresSetting {

    private String url;

    private String username;

    private String password;

    private String driver;

    private int poolSize;

    private String dialect;

    private boolean showSql;

    private boolean formatSql;

    private String defaultSchema;

    public DefaultPostgresSetting(String url,
                                  String username,
                                  String password,
                                  String driver,
                                  int poolSize,
                                  String dialect,
                                  boolean showSql,
                                  boolean formatSql,
                                  String defaultSchema) {

        this.url = url;
        this.username = username;
        this.password = password;
        this.driver = driver;
        this.poolSize = poolSize;
        this.dialect = dialect;
        this.showSql = showSql;
        this.formatSql = formatSql;
        this.defaultSchema = defaultSchema;
    }

    @Override
    public String getUrl() {

        return url;
    }

    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getDriver() {

        return driver;
    }

    @Override
    public int getPoolSize() {

        return poolSize;
    }

    @Override
    public String getDialect() {

        return dialect;
    }

    @Override
    public boolean isShowSql() {

        return showSql;
    }

    @Override
    public boolean isFormatSql() {

        return formatSql;
    }

    @Override
    public String getDefaultSchema() {

        return defaultSchema;
    }

}
