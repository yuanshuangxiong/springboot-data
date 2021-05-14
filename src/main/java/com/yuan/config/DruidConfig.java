package com.yuan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration//对应bean.xml
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")//绑定配置文件
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
    //后台监控 web.xml
    @Bean
    public ServletRegistrationBean StatViewServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //设置初始化参数，账号密码设置
        HashMap<String, String> initparameters = new HashMap<>();
        initparameters.put("loginUsername","admin");
        initparameters.put("loginPassword","123456");//登录key固定的
        //允许谁可以访问
        initparameters.put("allow","");//为空只能本机访问
        initparameters.put("kuangshen","192.168.11.123");//禁止访问名单
        bean.setInitParameters(initparameters);
        return bean;
    }
    //设置过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求
        Map<String ,String > initParames=new HashMap<>();
        //这些东西进行统计
        initParames.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParames);
        return bean;
    }
}
