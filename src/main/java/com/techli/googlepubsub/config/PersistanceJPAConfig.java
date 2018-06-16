package com.techli.googlepubsub.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;
@Configuration
@EnableJpaRepositories(basePackages = "com.techli.googlepubsub.repository")
public class PersistanceJPAConfig {

	@Autowired
	private HikariDataSource dataSource;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.techli.googlepubsub.model");
		JpaVendorAdapter jpaVendorAdaptor = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(jpaVendorAdaptor);
		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return properties;
		
	}
}
