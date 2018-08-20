package com.j2ee.servlet.filter;

import com.j2ee.servlet.wrapper.FilteredRequestWrapper;

import javax.servlet.*;
import java.io.IOException;

public  class RequestWrapperFilter implements Filter {
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter initialized");
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        System.out.println("Filter destroyed");
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setAttribute("age","10");//过滤器无法修改参数
        chain.doFilter(new FilteredRequestWrapper(request),response);//wrapper过滤参数
    }
}