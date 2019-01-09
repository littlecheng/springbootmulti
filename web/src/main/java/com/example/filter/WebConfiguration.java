package com.example.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <b>类中文名</b>
 * <p>类的描述，主要描述类的作用，使用说明，重要物性</p>
 *
 * @author 程涛(88396208)
 * @date 2018-12-25
 */
@Configuration
public class WebConfiguration {
    @Bean
    public RemoteIpFilter remoteIpFilter() {

        return new RemoteIpFilter();

    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(new MyFilter());

        registration.addUrlPatterns("/*");

        registration.addInitParameter("paramName", "paramValue");

        registration.setName("MyFilter");

        registration.setOrder(1);

        return registration;

    }

    public class MyFilter implements Filter {

        @Override

        public void destroy() {

            // TODO Auto-generated method stub

        }

        @Override

        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)

                throws IOException, ServletException {

            // TODO Auto-generated method stub

            HttpServletRequest request = (HttpServletRequest) srequest;

            System.out.println("this is MyFilter,url :" + request.getRequestURI());

            filterChain.doFilter(srequest, sresponse);

        }

        @Override

        public void init(FilterConfig arg0) throws ServletException {

            // TODO Auto-generated method stub

        }

    }
}
