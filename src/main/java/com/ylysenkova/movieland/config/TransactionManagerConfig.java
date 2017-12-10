package com.ylysenkova.movieland.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class TransactionManagerConfig {

    @Autowired
    private DataSource dataSource;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(){
=======
=======
>>>>>>> Stashed changes

    @Bean()
    public PlatformTransactionManager transactionManager() {
>>>>>>> Stashed changes
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
