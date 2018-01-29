package io.crscube.coop.cubetoken.cooperation.config;

import io.crscube.coop.cubetoken.cooperation.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Lee Tae Su 
 * @project CubeToken
 * @version 1.0
 * @since 2018-01-26
 * 
 * Application Context의 설정 내용을 아래 Config에서 기술한다
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Autowired
	public ApplicationConfig(AuthorizationInterceptor authorizationInterceptor) {
		this.authorizationInterceptor = authorizationInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor)
				.addPathPatterns("/**");
	}
}
