package ua.com.euniversity.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ua.com.euniversity.repository")
public class DataSourceConfig {

    @Value("${app.dataSource.postgres.jdbc-url}")
    private String datasourceUrl;

    @Value("${app.dataSource.postgres.username}")
    private String datasourceUsername;

    @Value("${app.dataSource.postgres.password}")
    private String datasourcePassword;

    @Bean
    public DataSource getDataSource() throws FlywayException {
        Jdbc3PoolingDataSource dataSource = new Jdbc3PoolingDataSource();
        dataSource.setURL(datasourceUrl);
        dataSource.setUser(datasourceUsername);
        dataSource.setPassword(datasourcePassword);
        Flyway flyway = Flyway.configure().dataSource(dataSource).cleanDisabled(false).load();
        flyway.clean();
        flyway.migrate();
        return dataSource;
    }
}
