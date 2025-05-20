package may.com.mm.infra.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import may.com.mm.infra.mysql.PrimaryPersistenceConfiguration;
import org.hibernate.dialect.MySQLDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(
    basePackages = "may.com.mm.*",
    enableDefaultTransactions = false,
    considerNestedRepositories = true)
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RequiredArgsConstructor
public class PostgresConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(PostgresConfiguration.class);

    @Qualifier("defaultPostgresSettings")
    private final PostgresSetting postgresSetting;

    @Bean(
        destroyMethod = "close")
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
        LOG.info("Username : <{}>", postgresSetting.getUsername());
        LOG.info("PoolSize : <{}>", postgresSetting.getPoolSize());
        config.setPoolName("Hikari-LOS");
        config.setJdbcUrl(postgresSetting.getUrl());
        config.setUsername(postgresSetting.getUsername());
        config.setPassword(postgresSetting.getPassword());
        config.setDriverClassName(postgresSetting.getDriver());

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", true);
        config.addDataSourceProperty("useLocalSessionState", true);
        config.addDataSourceProperty("rewriteBatchedStatements", true);
        config.addDataSourceProperty("cacheResultSetMetadata", true);
        config.addDataSourceProperty("cacheServerConfiguration", true);
        config.addDataSourceProperty("elideSetAutoCommits", true);
        config.addDataSourceProperty("maintainTimeStats", false);

        config.setMaximumPoolSize(postgresSetting.getPoolSize());
        config.setAutoCommit(false);

        return new HikariDataSource(config);

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        entityManagerFactoryBean.setPackagesToScan("may.com.mm.*");

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", postgresSetting.getDialect());
        jpaProperties.put("hibernate.show_sql", postgresSetting.isShowSql());
        jpaProperties.put("hibernate.format_sql", postgresSetting.isFormatSql());
        jpaProperties.put("hibernate.default_schema", postgresSetting.getDefaultSchema());

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;

    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {

        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);

    }

}
