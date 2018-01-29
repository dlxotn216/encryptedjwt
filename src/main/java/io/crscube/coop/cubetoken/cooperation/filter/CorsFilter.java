package io.crscube.coop.cubetoken.cooperation.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Lee Tae Su 
 * @project CubeToken
 * @version 1.0
 * @since 2018-01-26
 */
@WebFilter
@Slf4j
public class CorsFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	/**
	 * Cross-Origin Resource Sharing 요청을 처리
	 *
	 * @param servletRequest ServletRequest
	 * @param servletResponse ServletResponse
	 * @param filterChain chained filter
	 * @throws IOException .
	 * @throws ServletException .
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		log.info("doFilter -> Prepend");
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"X-Requested-With, Authorization, Origin, Content-Type, Version, encType, Range");
		response.setHeader("Access-Control-Expose-Headers",
				"X-Requested-With, Authorization, Origin, Content-Type,  Accept-Ranges, Content-Encoding, Content-Length, Content-Range");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		//Http OPTIONS 메소드인 경우 success response 처리
		if(Objects.equals(request.getMethod(), "OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			filterChain.doFilter(request, response);
		}
		log.info("doFilter -> Append");
	}
	
	@Override
	public void destroy() {
		
	}
}
