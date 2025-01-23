package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HarikConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://172.18.0.2:5432/jpa_database");
        config.setUsername("admin");
        config.setPassword("admin");
        config.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(config);
    }
}
