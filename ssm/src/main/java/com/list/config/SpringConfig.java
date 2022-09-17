package com.list.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/17 10:30
 */

@Configuration
@ComponentScan(
        basePackages = "com.list",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})}
)
//@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class SpringConfig {

//    @Value("${jdbc.username}")
//    private String username;
//    @Value("${jdbc.password}")
//    private String password;
//    @Value("${jdbc.driver}")
//    private String driverClassName;
//    @Value("${jdbc.url}")
//    private String url;

    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        Properties properties = new Properties();
        try {
            InputStream resourceAsStream = SpringConfig.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(resourceAsStream);
            druidDataSource.setUsername(properties.getProperty("jdbc.username"));
            druidDataSource.setPassword(properties.getProperty("jdbc.password"));
            druidDataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
            druidDataSource.setUrl(properties.getProperty("jdbc.url"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        druidDataSource.setUsername(username);
//        druidDataSource.setPassword(password);
//        druidDataSource.setDriverClassName(driverClassName);
//        druidDataSource.setUrl(url);

        return druidDataSource;
    }

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

    @Bean
    public SqlSessionFactoryBean sessionFactoryBean(DataSource dataSource, PageInterceptor pageInterceptor) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setEnvironment("POOLED");
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.list.pojo");
        Properties properties = new Properties();
        properties.setProperty("mapUnderscoreToCamelCase", "true");
        sqlSessionFactoryBean.setConfigurationProperties(properties);
        sqlSessionFactoryBean.setPlugins(pageInterceptor);
        return sqlSessionFactoryBean;
    }

    @Bean
    public TransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager =new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.list.mapper");
        return mapperScannerConfigurer;
    }

}
