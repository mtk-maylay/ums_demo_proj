package may.com.mm.infra.postgres;

public interface PostgresSetting {

    String getUrl();

    String getUsername();

    String getPassword();

    String getDriver();

    int getPoolSize();

    String getDialect();

    boolean isShowSql();

    boolean isFormatSql();

    String getDefaultSchema();

}
