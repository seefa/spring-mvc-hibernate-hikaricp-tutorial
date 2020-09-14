package ir.seefa.tutorial.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.util.Optional;
import java.util.Properties;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 09 Sep 2020 T 23:54:03
 */
// 1. Read spring-core-tutorial and spring-jdbc-mvc-tutorial codes before starting this project because primary annotations and mvc logic explained there
@Configuration
@ComponentScan(basePackages = "ir.seefa.tutorial.spring")
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
// 2. Enabling Spring JPA wrapper over introduced package
@EnableJpaRepositories(basePackages = "ir.seefa.tutorial.spring")
public class PersistenceConfiguration {

    // 3. Loading application configuration with Spring Environment class
    @Autowired
    private Environment env;

    // 4. apply main configuration Datasource from application.properties file and configuration datasource connection pool
    // 5. set HikariDataSource parameters and return HikariDataSource object instead of dbcp2 and jdbc data source
    @Bean
    public HikariDataSource dataSource() {

        final HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(env.getProperty("db.driver.class"));
        dataSource.setJdbcUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.addDataSourceProperty("cachePrepStmts", "true");
        dataSource.addDataSourceProperty("prepStmtCacheSize", "250");
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return dataSource;
    }

    // 6. define Hibernate bean factory and apply its data source and its additional configuration such as Database dialect or even Cache layer 2 for example HikariCP
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("ir.seefa.tutorial.spring");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalProperties());

        return entityManagerFactoryBean;
    }

    final Properties additionalProperties() {

        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        // apply compatible Hibernate HikariCP configuration

        // Maximum waiting time for a connection from the pool
        hibernateProperties.setProperty("hibernate.hikari.connectionTimeout", Optional.ofNullable(env.getProperty("hibernate.hikari.connectionTimeout")).orElse("10000"));
        // Minimum number of ideal connections in the pool
        hibernateProperties.setProperty("hibernate.hikari.minimumIdle", Optional.ofNullable(env.getProperty("hibernate.hikari.minimumIdle")).orElse("10"));
        // Maximum number of actual connection in the pool
        hibernateProperties.setProperty("hibernate.hikari.maximumPoolSize", Optional.ofNullable(env.getProperty("hibernate.hikari.maximumPoolSize")).orElse("20"));
        // Maximum time that a connection is allowed to sit ideal in the pool
        hibernateProperties.setProperty("hibernate.hikari.idleTimeout", Optional.ofNullable(env.getProperty("hibernate.hikari.idleTimeout")).orElse("20000"));

        return hibernateProperties;
    }

    // 7. define transaction manager bean for enabling Insert/Update/Delete transaction management with @Transactional annotation over Service layer
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

}
