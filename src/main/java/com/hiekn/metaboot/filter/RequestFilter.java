package com.hiekn.metaboot.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.Charset;

@Configuration
@ConditionalOnProperty(prefix = "filter",name = {"request"},havingValue = "true",matchIfMissing = true)
public class RequestFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
        System.out.println(httpRequest.getRequestURI());
        chain.doFilter(httpRequest,httpResponse);
	}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {

    }

}
