package com.nsdl.searchengine.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.nsdl.searchengine.*" })
public class SpringConfig {

	static {
		System.out.println("in configuration class");
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl("\"jdbc:mysql://localhost:5432/test");
		basicDataSource.setUsername("shubham");
		basicDataSource.setPassword("sd30");
		basicDataSource.setDriverClassName("org.postgresql.Drive");
		return basicDataSource;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(getDataSource());
		Properties properties = new Properties();
		properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.HBM2DDL_AUTO, "update");
		localSessionFactoryBean.setHibernateProperties(properties);
		localSessionFactoryBean.setPackagesToScan("com.nsdl.bean");
		return localSessionFactoryBean;
	}

	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;

	}

}
