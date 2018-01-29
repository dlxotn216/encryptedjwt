package io.crscube.coop.cubetoken.cooperation.base.controller;

import io.crscube.coop.cubetoken.cooperation.token.exception.AccessTokenInvalidException;
import io.crscube.coop.cubetoken.cooperation.token.exception.TokenExpirationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lee Tae Su 
 * @project CubeToken
 * @version 1.0
 * @since 2018-01-26
 *
 * 모든 Controller 들은 해당 클래스를 상속 받아야 한다.
 * 
 * AbstractController 내에서 모든 Request에서 발생 가능한 Exception을 처리한다
 */

public class AbstractController {
	
	@ExceptionHandler(value = {AccessTokenInvalidException.class})
	public ResponseEntity<Map<String, Object>> handleTokenExpirationException(TokenExpirationException exception) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("error", "Access token was expired");
		
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = {AccessTokenInvalidException.class})
	public ResponseEntity<Map<String, Object>> handleAccessTokenInvalidException(AccessTokenInvalidException exception) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("error", "Access token is invalid");
		
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Map<String, Object>> handleException(Exception exception) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("error", "Error Exception");
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
