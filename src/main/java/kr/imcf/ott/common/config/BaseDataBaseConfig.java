package kr.imcf.ott.common.config;

import com.google.common.collect.ImmutableMap;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author : rojae
 * @fileName : BaseDataBaseConfig.java
 * @date : 2021-08-03 오전 10:44
 * @description : Jpa, myBatis 정보 설정 클래스
 * ===========================================================
 * DATE            AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2021-08-03      rojae               최초 생성
 * 2021-08-27      rojae               Oracle9Dialect > Oracle9iDialect > MariaDB
 */
@Configuration
public class BaseDataBaseConfig {
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
	@Value("${spring.jpa.hibernate.dialect}")
	private String dialectName;

	@Value("${spring.jpa.hibernate.format_sql}")
	private String formatSql;
	
	@Value("${spring.jpa.open-in-view}")
	private String openInView;

	@Value("${spring.mybatis.config}")
	private String mybatisConfig;
	
	@Value("${spring.mybatis.mapper}")
	private String mybatisMapper;
	
	
	
    /**
     * JPA 정보 설정 *
     * * @param factory
     * * @param dbmsType
     */
    public void setConfigureEntityManagerFactory(LocalContainerEntityManagerFactoryBean factory, String dbmsType) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaPropertyMap(ImmutableMap.of(
                "hibernate.hbm2ddl.auto", ddlAuto,
                "hibernate.dialect", dialectName,
                "hibernate.open-in-view", openInView,
                "hibernate.format_sql", formatSql)
        );
        factory.afterPropertiesSet();
    }

    /**
     * * MySQL 정보 설정 *
     * * @param sessionFactoryBean
     * * @param dataSource * @throws
     * IOException
     */
    public void setConfigureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource)
            throws IOException {
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfig));
        sessionFactoryBean.setMapperLocations(resolver.getResources(mybatisMapper));
    }
}
