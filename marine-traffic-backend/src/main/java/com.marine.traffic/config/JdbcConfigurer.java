package com.marine.traffic.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfigurer {

    @Bean("mySQLDataSourceProperties")
    @ConfigurationProperties("spring.datasource-mysql")
    public DataSourceProperties mySQLDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("mySQLDataSource")
    public DataSource mySQLDataSource(@Qualifier("mySQLDataSourceProperties") DataSourceProperties dsProp) {
        DataSource dataSource = dsProp.initializeDataSourceBuilder().build();
        HikariConfig hc = new HikariConfig();
        hc.setAutoCommit(true);
        hc.setMaximumPoolSize(10);
        hc.setConnectionTimeout(10000);
        hc.setKeepaliveTime(30000);
        hc.setDataSource(dataSource);
        hc.setConnectionTestQuery("SELECT 1");
        return new HikariDataSource(hc);
    }

    @Bean("mySQLRepo")
    public NamedParameterJdbcTemplate initializeMySqlRepo(@Qualifier("mySQLDataSource") DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }

    @Bean("chDataSourceProperties")
    @ConfigurationProperties("spring.datasource-ch")
    public DataSourceProperties clickhouseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("chDataSource")
    public DataSource clickhouseDataSource(@Qualifier("chDataSourceProperties") DataSourceProperties dsProp) {
        return dsProp.initializeDataSourceBuilder().build();
    }

    @Bean("chRepo")
    public NamedParameterJdbcTemplate initializeClickhouseRepo(@Qualifier("chDataSource") DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }
}
