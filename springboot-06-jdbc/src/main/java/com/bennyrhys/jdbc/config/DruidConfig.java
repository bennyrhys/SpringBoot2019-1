package com.bennyrhys.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")//把配置文件不生效部分所有绑定进来
    @Bean
    public DataSource druid(){
       return new DruidDataSource();
    }

    //配置Druid的监控
    //1、配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){//配置进入管理后台的servlet
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String,String> initparm = new HashMap<>();
        initparm.put("loginUsername","root");
        initparm.put("loginPassword","root");

        initparm.put("allow",""); //允许谁访问,默认允许所有访问 deny拒绝访问

        bean.setInitParameters(initparm);
        return bean;
    }
    //2、配置一个web监控的filter
    @Bean
     public FilterRegistrationBean webStatFilter(){
         FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
         bean.setFilter(new WebStatFilter());
         Map<String,String> initparm = new HashMap<>();

         initparm.put("exclusions","*.js,*.css,/druid/*");

         bean.setUrlPatterns(Arrays.asList("/*"));
         bean.setInitParameters(initparm);
         return bean;
     }

}
