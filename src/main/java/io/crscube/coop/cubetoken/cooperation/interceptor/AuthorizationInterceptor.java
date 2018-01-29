package io.crscube.coop.cubetoken.cooperation.interceptor;

import io.crscube.coop.cubetoken.cooperation.base.RequestThreadContext;
import io.crscube.coop.cubetoken.cooperation.base.ThreadLocalInfo;
import io.crscube.coop.cubetoken.cooperation.token.exception.AccessTokenInvalidException;
import io.crscube.coop.cubetoken.cooperation.token.handler.TokenHandler;
import io.crscube.coop.cubetoken.cooperation.user.model.User;
import io.crscube.coop.cubetoken.cooperation.user.model.UserTokenType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lee Tae Su 
 * @project CubeToken
 * @version 1.0
 * @since 2018-01-26
 *
 * 인증 된 사용자의 권한 여부를 조사하는 Interceptor
 */
@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
	/**
	 * AccessToken 체크를 skip 할 URI 와 Http Method 를 Key, Value 로
	 * 저장하는 Immutable Map
	 *
	 * 반드시 Base context 를 포함하는 full URI 가 Key 가 되어야 함
	 * (mvc:interceptor 에서 처럼 wild card 사용은 지양)
	 *
	 * 모든 HttpMethod 를 허용해야 할 경우 ALL 로 설정 하지 말고
	 * context-common-{env}.xml 내에 선언 된 mvc:exclude-mapping 을 이용
	 *
	 */
	private static final Map<String, List<String>> skipURIs;
	
	static {
		Map<String, List<String>> map = new HashMap<>();
		map.put("/codes", Collections.singletonList("GET"));
		map.put("/i18ns/labels", Collections.singletonList("GET"));
		map.put("/users/check/password", Collections.singletonList("GET"));
		map.put("/users/check/password/answer", Collections.singletonList("POST"));
		map.put("/users/check/id", Collections.singletonList("GET"));
		
		skipURIs = Collections.unmodifiableMap(map);
	}
	
	@Autowired
	@Qualifier("AccessTokenHandler")
	private TokenHandler<User, UserTokenType> accesstokenHandler;
	
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		log.info("AuthorizationInterceptor::preHandle");
		String accessToken = httpServletRequest.getHeader("Authorization");
		String requestUri = httpServletRequest.getRequestURI();
		String method = httpServletRequest.getMethod();
		
		if(isSkipURI(requestUri, method))
			return true;
		
		if(StringUtils.isEmpty(accessToken))
			throw new AccessTokenInvalidException("AccessToken is invalid");
		
		User user = accesstokenHandler.parseTokenValue(accessToken);
		ThreadLocalInfo threadLocalInfo = RequestThreadContext.getLocal();
		threadLocalInfo.setUserKey(user.getUserKey());
		threadLocalInfo.setUserId(user.getUserId());
		
		long currentTime = System.currentTimeMillis();
		httpServletRequest.setAttribute("startTime", currentTime);
		return true;
	}
	
	/**
	 * http 요청에 대해 검증을 처리할 필요가 없는 지 조사
	 *
	 * @param requestURI 요청 URL
	 * @param requestMethod 요청 Method
	 * @return
	 */
	private boolean isSkipURI(String requestURI, String requestMethod) {
		List<String> allowedMethods = skipURIs.get(requestURI);
		return !CollectionUtils.isEmpty(allowedMethods) && (allowedMethods.contains(requestMethod));
	}
	
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
		log.info("AuthorizationInterceptor::postHandle");
		RequestThreadContext.remoteLocal();
	}
	
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		log.info("AuthorizationInterceptor::afterCompletion");
	}
}
