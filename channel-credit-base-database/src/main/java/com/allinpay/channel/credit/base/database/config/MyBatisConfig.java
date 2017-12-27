package com.allinpay.channel.credit.base.database.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ConditionalOnMissingBean(MyBatisConfig.class)
//@EnableConfigurationProperties(MyBatisConfig.class)
@MapperScan("com.allinpay.**.mapper")
@ConfigurationProperties(prefix="mybatis")
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;
    
    private String typeAliasesPackage = "com.allinpay.**.model";
    
    private String mapperLocations = "classpath*:mapper/**/*.xml";
    
    private String configLocation = "classpath:mybatis/mybatis-config.xml";

//    private RelaxedPropertyResolver propertyResolver;


    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
            sessionFactory
                    .setMapperLocations(new PathMatchingResourcePatternResolver()
                            .getResources(mapperLocations));
            sessionFactory
                    .setConfigLocation(new PathMatchingResourcePatternResolver()
                            .getResource(configLocation));

            return sessionFactory.getObject();
        } catch (Exception e) {
        	log.error("Create sqlSessionFactory failed",e);
            log.warn("Could not configure mybatis session factory");
            return null;
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @ConditionalOnMissingBean(PlatformTransactionManager.class)
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

//    public void setEnvironment(Environment environment) {
//        this.propertyResolver = new RelaxedPropertyResolver(environment, "mybatis.");
//    }
}
