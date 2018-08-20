package com.j2ee.servlet.wrapper;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class FilteredRequestWrapper extends HttpServletRequestWrapper {

    public FilteredRequestWrapper(ServletRequest request) {
        super((HttpServletRequest) request);
    }

    public String getParameter(String paramName) {
        String value = super.getParameter(paramName);
        if ("studentname".equals(paramName)) {
            // 更改请求参数的值
            value="----------->学生 "+value;
        }
        return value;
    }

    public String[] getParameterValues(String paramName) {
        String values[] = super.getParameterValues(paramName);
        return values;
    }
}