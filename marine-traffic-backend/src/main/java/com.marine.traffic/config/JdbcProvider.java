package com.marine.traffic.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class JdbcProvider {
    private final NamedParameterJdbcTemplate mysqlRepo;
    private final NamedParameterJdbcTemplate clickhouseRepo;

    private JdbcProvider(@Qualifier("mySQLRepo") NamedParameterJdbcTemplate mysqlRepo,
                         @Qualifier("chRepo") NamedParameterJdbcTemplate clickhouseRepo) {
        this.mysqlRepo = mysqlRepo;
        this.clickhouseRepo = clickhouseRepo;
    }
    
    public NamedParameterJdbcTemplate mySQL() {
        return this.mysqlRepo;
    }

    public NamedParameterJdbcTemplate clickhouse() {
        return this.clickhouseRepo;
    }

    public boolean isMySQLConnected() {
        try (Connection connection = this.mysqlRepo.getJdbcTemplate().getDataSource().getConnection()) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean isClickHouseConnected() {
        try (Connection connection = this.clickhouseRepo.getJdbcTemplate().getDataSource().getConnection()) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
