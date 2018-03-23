package com.hiekn.metaboot.filter;

import com.hiekn.metaboot.service.TokenManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Configuration
@Provider
public class ResponseFilter implements ContainerResponseFilter{

    @Context
    private HttpServletResponse response;

    @Autowired
    private TokenManageService tokenManageService;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
        String token = tokenManageService.createNewToken();
        if(token != null){
            response.setHeader("Authorization",token);
        }

    }

}
