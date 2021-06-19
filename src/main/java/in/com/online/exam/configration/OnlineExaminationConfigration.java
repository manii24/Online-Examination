package in.com.online.exam.configration;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import in.com.online.exam.util.DataUtility;




@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("in.com.online.exam")
@PropertySource({"classpath:persistence-mysql.properties"})
public class OnlineExaminationConfigration implements WebMvcConfigurer {

	private Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	private Environment env;

	@Bean
	public DataSource getHibDataSourse() {
		log.info("GetHibDataSourse method start");
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		// testing
		try {
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			cpds.setJdbcUrl(env.getProperty("jdbc.url"));
			cpds.setUser(env.getProperty("jdbc.user"));
			cpds.setPassword(env.getProperty("jdbc.password"));
			cpds.setInitialPoolSize(DataUtility.getInt(env.getProperty("connection.pool.initialPoolSize")));
			cpds.setMinPoolSize(DataUtility.getInt(env.getProperty("connection.pool.minPoolSize")));
			cpds.setMaxPoolSize(DataUtility.getInt(env.getProperty("connection.pool.maxPoolSize")));
			cpds.setMaxIdleTime(DataUtility.getInt(env.getProperty("connection.pool.maxIdleTime")));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			throw new ApplicationContextException("Exception in GetHibDataSourse " + e.getMessage());
		}
		log.info("GetHibDataSourse method end");
		return cpds;
	}
	
	
	

	private Properties getHibernateProperties() {
		log.info("getHibernatePropertirs method start");

		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		prop.setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
		log.info("getHibernateProperies method end");
		return prop;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		log.info("getSessionFactory method start");

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getHibDataSourse());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));

		log.info("getSessionFactory method end");
		return sessionFactory;
	}
	
	   @Bean
	    public JavaMailSender getJavaMailSender() 
	    {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(465);
	          
	        mailSender.setUsername("admin@gmail.com");
	        mailSender.setPassword("password");
	          
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	          
	        return mailSender;
	    }
	

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		log.info("transactionManager method start");

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		log.info("transactionManager method end");
		return txManager;
	}

	@Bean(name = "viewResolver")
	public ViewResolver getViewResolver() {

		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);

		return viewResolver;
	}

	@Bean(name = "tilesConfigure")
	public TilesConfigurer getTilesConfigure() {
		TilesConfigurer tilesConfigure = new TilesConfigurer();
		tilesConfigure.setDefinitions("/WEB-INF/tiles.xml");
		return tilesConfigure;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
	@Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         
        //Using gmail
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("hariom.mukati.97@gmail.com");
        mailSender.setPassword("avhariom");
         
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
         
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
	

	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

}