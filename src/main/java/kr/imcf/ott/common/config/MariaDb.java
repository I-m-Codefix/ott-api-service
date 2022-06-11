package kr.imcf.ott.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/** * maria DB 설정 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "mariaEntityManagerFactory", transactionManagerRef = "mariaTransactionManager", basePackages = {
		"kr.imcf.ott.persistence.repository" })
@MapperScan(sqlSessionFactoryRef = "mariaSqlSessionFactory", basePackages = { "kr.imcf.ott.persistence.mapper" })
public class MariaDb extends BaseDataBaseConfig {
	/**
	 * * 데이터베이스 연결 *
	 *
	 * <pre>
	 *  * NOTE: 데이터베이스 설정 정보는 application.yml에 설정된 * dataSource 설정 정보이다. * 여기 정보를 가져오는 부분이 @ConfigurationProperties * 이다. *
	 * </pre>
	 *
	 * * * @return
	 */
	@Primary
	@Bean(name = "mariaDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.mariadb")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	/** * EntityManagerFactory 생성 * * @param dataSource * @return */
	@Primary
	@Bean(name = "mariaEntityManagerFactory")
	public EntityManagerFactory entityManagerFactory(@Qualifier("mariaDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("kr.imcf.ott.domain.entity");
		factory.setPersistenceUnitName("mariadb");
		setConfigureEntityManagerFactory(factory, "m");

		return factory.getObject();
	}

	/** * SessionFactory 생성 * * @param dataSource * @return * @throws Exception */
	@Bean(name = "mariaSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("mariaDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		setConfigureSqlSessionFactory(sessionFactoryBean, dataSource);
		return sessionFactoryBean.getObject();
	}

	/**
	 * * Transaction 설정 * * @param entityManagerFactory * @return
	 */
	@Primary
	@Bean(name = "mariaTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("mariaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
