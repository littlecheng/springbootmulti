package com.example.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <b>类中文名</b>
 * <p>类的描述，主要描述类的作用，使用说明，重要物性</p>
 *
 * @author 程涛(88396208)
 * @date 2018-12-25
 */
@Configuration
@MapperScan(basePackages = {DataSourceConfiguration.MAPPER_PACKAGE})
public class DataSourceConfiguration {
    public static final String MAPPER_PACKAGE = "com.example.mapper";

    public static final String MAPPER_XML_PACKAGE = "classpath:mapper/*.xml";

    public static final String MYBATIS_BEAN_PACKAGE = "com.example";

    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(name = "dataSource")
    public DataSource dataSourceConfig() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceConfig());

        //设置扫描 mybatis-config.xml
        sqlSessionFactoryBean.setConfigLocation(null);

        //设置扫描mapper.xml
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(MAPPER_XML_PACKAGE);
        sqlSessionFactoryBean.setMapperLocations(resources);

        //设置扫描实体类
        sqlSessionFactoryBean.setTypeAliasesPackage(MYBATIS_BEAN_PACKAGE);

        return sqlSessionFactoryBean.getObject();
    }


    @Primary
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate popSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceConfig());
    }

}
