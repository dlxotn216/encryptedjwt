package io.crscube.coop.cubetoken.cooperation.token.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project cooperation
 * @since 2018-01-23
 *
 * 토큰이 만료되었을 때 발생하는 Exception
 */
public class TokenExpirationException extends NestedRuntimeException {

    public TokenExpirationException(String msg) {
        super(msg);
    }

    public TokenExpirationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
