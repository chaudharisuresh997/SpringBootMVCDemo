package com.main;

import java.util.Properties;

import javax.sql.DataSource;
 
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.main.model.Game;
 

 
@Configuration

@EnableTransactionManagement
@ComponentScans(value = { 
    @ComponentScan("com.main.dao"),
    @ComponentScan("com.main.service"),
    @ComponentScan("com.main.conteoller")
  })
public class AppConfig {
	
		public static final String driver="org.hsqldb.jdbcDriver";//"org.h2.Driver";
		public static final String url="jdbc:hsqldb:mem://productDb";//"jdbc:hsqldb:hsql://localhost:9137/webappdb";//"jdbc:h2:~/test";
		//server.remote_open=true
		public static final String username="sa";
		public static final String password="";
	 
		//# Hibernate properties
		public static final String show_sql="true";
		public static final String hbm2ddl="update";
		public static final String dialect="org.hibernate.dialect.HSQLDialect";//"org.hibernate.dialect.H2Dialect";
	   /*@Autowired
	   private Environment env;
	*/
	   @Bean
	   public DataSource getDataSource() {
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(driver);
	      dataSource.setUrl(url);
	      dataSource.setUsername(username);
	      dataSource.setPassword(password);
	      return dataSource;
	   }
	   
	   @Bean
	   public LocalSessionFactoryBean getSessionFactory() {
	      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	      factoryBean.setDataSource(getDataSource());
	      
	      Properties props=new Properties();
	      props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	      props.setProperty("hibernate.dialect", dialect);
	     // props.setProperty("hibernate.show_sql", true);
	      //props.put("hibernate.hbm2ddl.auto", "update");
	 
	      factoryBean.setHibernateProperties(props);
	      factoryBean.setAnnotatedClasses(Game.class);
	      return factoryBean;
	   }
	 
	   @Bean
	   public HibernateTransactionManager getTransactionManager() {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	      transactionManager.setSessionFactory(getSessionFactory().getObject());
	      return transactionManager;
	   }
}
