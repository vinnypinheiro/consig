package br.com.solutis.desafio.helper;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcConfiguration {

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://10.1.1.239:5432/associacao");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate()
    {
        NamedParameterJdbcTemplate retBean
                = new NamedParameterJdbcTemplate(dataSource());
        return retBean;
    }

    @Bean
    public DataSourceTransactionManager txnManager()
    {
        DataSourceTransactionManager txnManager
                = new DataSourceTransactionManager(dataSource());
        return txnManager;
    }

}
