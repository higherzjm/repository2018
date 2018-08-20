package com.j2ee.servlet.filter;

import com.j2ee.servlet.wrapper.FilterResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
 
public class ResponseWrapperFilter implements Filter {
 
	@Override
    public void destroy() {
		// TODO Auto-generated method stub
 
	}
 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterResponseWrapper wrapper = new FilterResponseWrapper((HttpServletResponse)response);
		chain.doFilter(request, wrapper);
		String result = wrapper.getResult();
		result = result.replace("中华", "中华人民");
		// 输出最终的结果
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();
	}
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
 
	}
 
}