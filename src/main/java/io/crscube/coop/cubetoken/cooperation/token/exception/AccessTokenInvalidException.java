package io.crscube.coop.cubetoken.cooperation.token.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author Lee Tae Su 
 * @project CubeToken
 * @version 1.0
 * @since 2018-01-29
 */
public class AccessTokenInvalidException extends NestedRuntimeException {
	
	public AccessTokenInvalidException(String msg) {
		super(msg);
	}
	
	public AccessTokenInvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
