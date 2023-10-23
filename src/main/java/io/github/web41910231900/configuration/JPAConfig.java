package io.github.web41910231900.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("io.github.web41910231900")
@EnableTransactionManagement
public class JPAConfig {

    @Bean
    public DataSource getSource() {
        Properties info = new Properties();
        try {
            info.load(this.getClass().getResourceAsStream("/db.cfg"));
        } catch (IOException ignored) { } // impossible
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/studs");
        dataSource.setUsername(info.getProperty("user"));
        dataSource.setPassword(info.getProperty("password"));

        return dataSource;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getSource());
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setPackagesToScan("io.github.web41910231900");

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        var object = entityManagerFactory().getObject();
        assert object != null;
        return new JpaTransactionManager(object);
    }
}
