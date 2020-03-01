package com.bennyrhys.springboot04restfulcurd.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter process……");
        filterChain.doFilter(servletRequest, servletResponse);//放行
    }

    @Override
    public void destroy() {

    }
}
