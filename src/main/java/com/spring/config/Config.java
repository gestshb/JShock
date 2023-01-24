package com.spring.config;


import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.spring.repository")
@ComponentScan(value = {"com.spring.controller",
        "com.spring.service",
        "com.spring.service.impl",
        "com.spring.config",
        "com.spring.repository"})
public class Config {

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./data/database;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;

    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactoryBean.getObject() );
        return transactionManager;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("com.spring.bean");
        em.setJpaVendorAdapter(jpaVendorAdapter);
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put(Environment.FORMAT_SQL, true);
        jpaProperties.put(Environment.PHYSICAL_NAMING_STRATEGY,
                "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        //jpaProperties.put(Environment.DEFAULT_SCHEMA, "base_department");
        jpaProperties.put(Environment.HBM2DDL_AUTO, "update");
        jpaProperties.put(Environment.SHOW_SQL, false);
        jpaProperties.put("hibernate.jdbc.lob.non_contextual_creation", true);
        jpaProperties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        em.setJpaPropertyMap(jpaProperties);
        return em;
    }
}
