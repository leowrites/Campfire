package main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@ConfigurationProperties(prefix = "spring.datasource")
@Configuration
public class DataSourceBean {

    @Bean
    public DataSource setDataSource()
    {
        return DataSourceBuilder.create().build();
    }
}
